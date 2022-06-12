package com.car.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.car.controller.request.BookCarRequest;
import com.car.dao.CarDAO;
import com.car.dao.OrderDAO;
import com.car.entity.Car;
import com.car.entity.Order;
import com.car.entity.enums.ExceptionEnum;
import com.car.entity.enums.OrderStatusEnum;
import com.car.entity.exception.BaseException;
import com.car.service.CarRentalService;
import com.car.utils.Assert;
import com.car.utils.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Car Rental Service
 *
 * @author Gandalf
 * @since 2022-06-11 01:12
 */
@Slf4j
@Service
public class CarRentalServiceImpl implements CarRentalService {

    @Resource
    private CarDAO carDAO;
    @Resource
    private OrderDAO orderDAO;
    @Resource
    private TransactionTemplate transactionTemplate;
    @Resource(name = "carRentalScheduler")
    private ScheduledExecutorService scheduler;
    @Resource
    private IdWorker idWorker;

    @Override
    public void bookCar(BookCarRequest request, Long carId) {
        Assert.isTrue(request.getGmtStart() < request.getGmtEnd(), ExceptionEnum.PARAM_ILLEGAL);
        Assert.isTrue(request.getGmtEnd() > System.currentTimeMillis(), ExceptionEnum.PARAM_ILLEGAL);

        Car car = carDAO.getById(carId);
        Assert.notNull(car, ExceptionEnum.CAR_NOT_EXISTS);

        // TODO: Gandalf 2022/6/12 锁粒度
        synchronized (this) {
            List<Order> ordersInUs = orderDAO.getInUse(carId, request.getGmtStart(), request.getGmtEnd());
            Assert.empty(ordersInUs, ExceptionEnum.CAR_IS_SERVING);
            try {
                transactionTemplate.execute(status -> {
                    Order order = Order.builder()
                            .id(idWorker.nextId())
                            .uid(request.getUid())
                            .carId(carId)
                            .orderStatus(OrderStatusEnum.VALID.getStatus())
                            .gmtStart(request.getGmtStart())
                            .gmtEnd(request.getGmtEnd())
                            .build();
                    orderDAO.createOrder(order);

                    scheduler.schedule(() -> {
                        // start a delay job to check order status
                        finishOrder(order);
                    }, request.getGmtEnd() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
                    return true;
                });
            } catch (Exception e) {
                log.error("fail to book car, carId:{}", carId, e);
                throw new BaseException(ExceptionEnum.SYSTEM_ERROR);
            }
        }
    }

    @Override
    public void finishOrder(Long orderId, String uid) {
        Order o = orderDAO.getById(orderId);
        Assert.notNull(o, ExceptionEnum.ORDER_NOT_EXISTS);
        Assert.isTrue(o.getUid().equals(uid), ExceptionEnum.ORDER_NOT_EXISTS);
        this.finishOrder(o);
    }

    private void finishOrder(Order order) {
        boolean success = false;
        for (int i = 0; i < 3; i++) {
            if (success) {
                break;
            }
            try {
                if (OrderStatusEnum.getByStatus(order.getOrderStatus()).equals(OrderStatusEnum.VALID)) {
                    order.setOrderStatus(OrderStatusEnum.FINISHED.getStatus());
                    orderDAO.updateById(order);
                }
                success = true;
            } catch (Exception e) {
                log.error("fail to finish order:{}", JSONObject.toJSON(order), e);
            }
        }
    }

    @Override
    public List<Car> listCars(List<Long> carIds, Integer type, Long startTime, Long endTime) {
        List<Long> carIdExclude = new ArrayList<>();
        if (startTime != null && endTime != null) {
            Assert.isTrue(startTime < endTime, ExceptionEnum.PARAM_ILLEGAL);
            Assert.isTrue(endTime > System.currentTimeMillis(), ExceptionEnum.PARAM_ILLEGAL);
            List<Order> ordersInUse = orderDAO.getInUse(null, startTime, endTime);
            if(!CollectionUtils.isEmpty(ordersInUse)){
                carIdExclude = ordersInUse.stream().map(Order::getCarId).collect(Collectors.toList());
            }
        }
        return carDAO.listCars(carIds, type, carIdExclude);
    }

    @Override
    public List<Order> listOrders(String uid) {
        return orderDAO.listOrders(uid);
    }
}

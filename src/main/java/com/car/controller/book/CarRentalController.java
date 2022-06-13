package com.car.controller.book;

import com.car.config.request.BookCarRequest;
import com.car.config.request.QueryCarRequest;
import com.car.config.response.OrderResponse;
import com.car.config.response.Response;
import com.car.entity.Car;
import com.car.entity.Order;
import com.car.entity.enums.ExceptionEnum;
import com.car.entity.exception.BaseException;
import com.car.service.CarRentalService;
import com.car.utils.Assert;
import com.car.utils.DateUtil;
import com.car.utils.UserUtil;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * car rental controller
 *
 * @author Gandalf
 * @since 2022-06-11 01:37
 */
@RestController
@RequestMapping("/car-rental")
public class CarRentalController {

    @Resource
    private CarRentalService carRentalService;

    @GetMapping("/cars")
    public Response<List<Car>> listCars(QueryCarRequest request) {
        Assert.notNull(request.getType(), ExceptionEnum.PARAM_ILLEGAL);
        Assert.notBlank(request.getStartTime(), ExceptionEnum.PARAM_ILLEGAL);
        Assert.notBlank(request.getEndTime(), ExceptionEnum.PARAM_ILLEGAL);
        Long startTime = null;
        Long endTime = null;
        try {
            startTime = DateUtil.convertTimeToLong(request.getStartTime());
            endTime = DateUtil.convertTimeToLong(request.getEndTime());
        } catch (Exception e) {
            throw new BaseException(ExceptionEnum.PARAM_ILLEGAL);
        }
        List<Car> cars = carRentalService.listCars(null, request.getType(),
                startTime, endTime);
        return Response.success(cars);
    }

    @GetMapping("/user/orders")
    public Response<List<OrderResponse>> listOrders() {
        List<Order> orders = carRentalService.listOrders(UserUtil.getUid());
        Map<Long, Car> carMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(orders)) {
            List<Long> carIds = orders.stream().map(Order::getCarId).collect(Collectors.toList());
            List<Car> cars = carRentalService.listCars(carIds, null, null, null);
            if (!CollectionUtils.isEmpty(cars)) {
                carMap = cars.stream().collect(Collectors.toMap(Car::getId, Function.identity()));
            }
        }
        Map<Long, Car> finalCarMap = carMap;
        List<OrderResponse> orderResponses = orders.stream().map(order -> OrderResponse.builder()
                .car(finalCarMap.get(order.getCarId()))
                .id(order.getId().toString())
                .orderStatus(order.getOrderStatus())
                .gmtStart(DateUtil.convertTimeToString(order.getGmtStart()))
                .gmtEnd(DateUtil.convertTimeToString(order.getGmtEnd()))
                .build())
                .collect(Collectors.toList());
        return Response.success(orderResponses);
    }

    @PostMapping("/cars/{car_id}/book")
    public Response<Boolean> bookCar(@RequestBody BookCarRequest request, @PathVariable("car_id") String carId) {
        Assert.notBlank(carId, ExceptionEnum.PARAM_ILLEGAL);
        Assert.notBlank(request.getGmtStart(), ExceptionEnum.PARAM_ILLEGAL);
        Assert.notBlank(request.getGmtEnd(), ExceptionEnum.PARAM_ILLEGAL);
        carRentalService.bookCar(request, Long.valueOf(carId));
        return Response.success(true);
    }

    @PostMapping("/orders/{order_id}/finish")
    public Response<Boolean> finishOrder(@PathVariable("order_id") String orderId) {
        Assert.notBlank(orderId, ExceptionEnum.PARAM_ILLEGAL);
        carRentalService.finishOrder(Long.valueOf(orderId), UserUtil.getUid());
        return Response.success(true);
    }

}

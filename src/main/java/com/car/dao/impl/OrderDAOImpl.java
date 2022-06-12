package com.car.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.car.dao.OrderDAO;
import com.car.dao.mapper.OrderMapper;
import com.car.entity.Order;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * order dao implement
 *
 * @author Gandalf
 * @since 2022-06-11 14:38
 */
@Repository
public class OrderDAOImpl implements OrderDAO {

    @Resource
    private OrderMapper orderMapper;

    @Override
    public void createOrder(Order order) {
        orderMapper.insert(order);
    }

    @Override
    public Order getById(Long id) {
        return orderMapper.selectById(id);
    }

    @Override
    public void updateById(Order o) {
        orderMapper.updateById(o);
    }

    @Override
    public List<Order> getInUse(Long carId, Long gmtStart, Long gmtEnd) {
        return orderMapper.queryInUse(carId, gmtStart, gmtEnd);
    }

    @Override
    public List<Order> listOrders(String uid) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Order::getUid, uid).eq(Order::getStatus, 1);
        return orderMapper.selectList(queryWrapper);
    }
}

package com.car.dao;

import com.car.entity.Order;

import java.util.List;

/**
 * order DAO
 *
 * @author Gandalf
 * @since 2022-06-11 18:18
 */
public interface OrderDAO {

    void createOrder(Order order);

    Order getById(Long id);

    void updateById(Order o);

    List<Order> getInUse(Long carId, Long gmtStart, Long gmtEnd);

    List<Order> listOrders(String uid);
}

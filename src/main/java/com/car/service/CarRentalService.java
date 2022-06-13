package com.car.service;

import com.car.config.request.BookCarRequest;
import com.car.entity.Car;
import com.car.entity.Order;

import java.util.List;

/**
 * car rental service
 *
 * @author Gandalf
 * @since 2022-06-11 01:12
 */
public interface CarRentalService {

    List<Car> listCars(List<Long> carIds, Integer type, Long startTime, Long endTime);

    void finishOrder(Long orderId, String uid);

    void bookCar(BookCarRequest request, Long carId);

    List<Order> listOrders(String uid);
}

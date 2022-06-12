package com.car.dao;

import com.car.entity.Car;

import java.util.List;

/**
 * car DAO
 *
 * @author Gandalf
 * @since 2022-06-11 01:13
 */
public interface CarDAO {

    List<Car> listCars(List<Long> carIds, Integer type, List<Long> carIdExclude);

    Car getById(Long carId);
}

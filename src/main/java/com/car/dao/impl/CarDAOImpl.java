package com.car.dao.impl;

import com.car.dao.CarDAO;
import com.car.dao.mapper.CarMapper;
import com.car.entity.Car;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * car dao implement
 *
 * @author Gandalf
 * @since 2022-06-11 14:38
 */
@Repository
public class CarDAOImpl implements CarDAO {

    @Resource
    private CarMapper carMapper;

    @Override
    public List<Car> listCars(List<Long> carIds, Integer type, List<Long> carIdExclude) {
        return carMapper.listCars(carIds, type, carIdExclude);
    }

    @Override
    public Car getById(Long carId) {
        return carMapper.selectById(carId);
    }
}

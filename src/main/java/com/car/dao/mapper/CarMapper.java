package com.car.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.car.entity.Car;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * car mapper
 *
 * @author Gandalf
 * @since 2022-06-11 14:15
 */
@Mapper
public interface CarMapper extends BaseMapper<Car> {
    List<Car> listCars(List<Long> carIds, Integer type, List<Long> carIdExclude);
}

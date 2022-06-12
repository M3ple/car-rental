package com.car.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.car.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * order mapper
 *
 * @author Gandalf
 * @since 2022-06-11 14:15
 */
public interface OrderMapper extends BaseMapper<Order> {

    List<Order> queryInUse(@Param("carId") Long carId, @Param("gmtStart") Long gmtStart, @Param("gmtEnd") Long gmtEnd);
}

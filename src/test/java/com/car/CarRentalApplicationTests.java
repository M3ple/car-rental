package com.car;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.car.dao.mapper.CarMapper;
import com.car.dao.mapper.OrderMapper;
import com.car.entity.Car;
import com.car.entity.enums.CarTypeEnum;
import com.car.utils.MailUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class CarRentalApplicationTests {

    @Resource
    private CarMapper carMapper;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private MailUtil mailUtil;

    @Test
    void orderTest() {
        orderMapper.queryInUse(1L, 123L, 123L);
    }

    @Test
    void mail() {
        mailUtil.sendSimpleMail("maple-cjx@yopmail.net", "test", "test");
    }

    @Test
    void contextLoads() {
        Car car = Car.builder()
                .type(CarTypeEnum.TOYOTA_CAMRY.getType())
                .status(1)
                .gmtCreate(System.currentTimeMillis())
                .gmtModify(System.currentTimeMillis())
                .build();
        Assertions.assertEquals(1, carMapper.insert(car));

        List<Car> cars = carMapper.selectList(new QueryWrapper<>());
        System.out.println(JSONObject.toJSONString(cars));
    }


}

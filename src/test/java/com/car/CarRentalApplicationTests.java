package com.car;

import com.car.config.request.BookCarRequest;
import com.car.entity.Car;
import com.car.entity.Order;
import com.car.entity.exception.BaseException;
import com.car.service.CarRentalService;
import com.car.utils.DateUtil;
import com.car.utils.MailUtil;
import com.car.utils.UserUtil;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class CarRentalApplicationTests {

    @Resource
    private CarRentalService carRentalService;
    @Resource
    private MailUtil mailUtil;

    @BeforeAll
    static void init() {
        UserUtil.setUid("maple-cjx@yopmail.net");
    }

    @Test
    @org.junit.jupiter.api.Order(1)
    void listCarsTest() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endDateTime = now.plusDays(2);
        long startTime = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long endTime = endDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        List<Car> cars = carRentalService.listCars(null, -1, startTime, endTime);
        Assertions.assertEquals(cars.size(), 4);
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    void bookCarTest() {
        Assertions.assertDoesNotThrow(() -> {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime endDateTime = now.plusDays(2);
            long startTime = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            long endTime = endDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            BookCarRequest request = new BookCarRequest();
            request.setGmtStart(DateUtil.convertTimeToString(startTime));
            request.setGmtEnd(DateUtil.convertTimeToString(endTime));
            carRentalService.bookCar(request, 1L);
        });
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    void bookCarFailTest() {
        try {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime endDateTime = now.plusDays(2);
            long startTime = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            long endTime = endDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            BookCarRequest request = new BookCarRequest();
            request.setGmtStart(DateUtil.convertTimeToString(startTime));
            request.setGmtEnd(DateUtil.convertTimeToString(endTime));
            carRentalService.bookCar(request, 1L);
        } catch (Exception e) {
            Assertions.assertTrue(e instanceof BaseException);
        }
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    void bookCarAtIndependentTimeTest() {
        LocalDateTime startDateTime = LocalDateTime.now().plusDays(3);
        LocalDateTime endDateTime = startDateTime.plusDays(2);
        long startTime = startDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long endTime = endDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        BookCarRequest request = new BookCarRequest();
        request.setGmtStart(DateUtil.convertTimeToString(startTime));
        request.setGmtEnd(DateUtil.convertTimeToString(endTime));
        carRentalService.bookCar(request, 1L);
    }

    @Test
    @org.junit.jupiter.api.Order(5)
    void listOrderTest() {
        List<Order> orders = carRentalService.listOrders(UserUtil.getUid());
        Assertions.assertNotNull(orders);
    }

    @Test
    @org.junit.jupiter.api.Order(6)
    void finishOrderTest() {
        List<Order> orders = carRentalService.listOrders(UserUtil.getUid());
        Assertions.assertNotNull(orders);
        Assertions.assertDoesNotThrow(() -> carRentalService.finishOrder(orders.get(0).getId(), UserUtil.getUid()));
    }

    @Test
    @org.junit.jupiter.api.Order(0)
    void mailTest() {
        mailUtil.sendSimpleMail(UserUtil.getUid(), "test", "test");
    }

}

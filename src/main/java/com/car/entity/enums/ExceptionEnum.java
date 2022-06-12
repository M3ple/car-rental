package com.car.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * TODO 类描述，创建后请修改。
 *
 * @author Gandalf
 * @since 2022-06-11 01:58
 */
@Getter
@AllArgsConstructor
public enum ExceptionEnum {

    OK("200", "ok"),

    SYSTEM_ERROR("500", "system error"),

    NO_ACCESS("403", "no access"),

    PARAM_ILLEGAL("10001", "param illegal"),

    CAR_IS_SERVING("10002", "this car is serving"),

    CAR_NOT_EXISTS("10003", "car not exists"),

    ORDER_NOT_EXISTS("10004", "order not exists"),
    ;

    private String code;

    private String message;


}

package com.car.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * exception enum
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

    USER_ALREADY_EXISTS("10005", "user already exists"),

    USERNAME_OR_PASSWORD_NOT_CORRECT("10006", "username or password not correct"),

    PLEASE_LOG_IN("10007", "please log in"),
    ;

    private String code;

    private String message;


}

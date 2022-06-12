package com.car.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.TimeUnit;

/**
 * car type enumeration
 *
 * @author Gandalf
 * @since 2022-06-11 01:22
 */
@Getter
@AllArgsConstructor
public enum RentTypeEnum {

    ONE_HOUR(1, TimeUnit.HOURS),
    ONE_DAY(2, TimeUnit.DAYS),
    ;

    Integer type;
    TimeUnit timeUnit;

    public static RentTypeEnum getByType(Integer type){
        if (type == null) {
            return null;
        }
        for (RentTypeEnum rentTypeEnum : RentTypeEnum.values()) {
            if (rentTypeEnum.type.equals(type)) {
                return rentTypeEnum;
            }
        }
        return null;
    }
}

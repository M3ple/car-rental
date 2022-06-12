package com.car.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * order status enumeration
 *
 * @author Gandalf
 * @since 2022-06-11 01:22
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnum {

    VALID(1),
    FINISHED(2),
    ;

    Integer status;

    public static OrderStatusEnum getByStatus(Integer status){
        if (status == null) {
            return null;
        }
        for (OrderStatusEnum orderStatusEnum : OrderStatusEnum.values()) {
            if (orderStatusEnum.status.equals(status)) {
                return orderStatusEnum;
            }
        }
        return null;
    }
}

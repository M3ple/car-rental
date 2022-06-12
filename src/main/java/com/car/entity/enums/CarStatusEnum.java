package com.car.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * car status enumeration
 *
 * @author Gandalf
 * @since 2022-06-11 01:22
 */
@Getter
@AllArgsConstructor
public enum CarStatusEnum {

    FREE(1),
    IN_USE(2);

    Integer status;
}

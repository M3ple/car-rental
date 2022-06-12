package com.car.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * car type enumeration
 *
 * @author Gandalf
 * @since 2022-06-11 01:22
 */
@Getter
@AllArgsConstructor
public enum CarTypeEnum {

    TOYOTA_CAMRY(1),
    BMW_650(2);

    Integer type;
}

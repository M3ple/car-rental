package com.car.entity;

import com.car.entity.base.BaseDO;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * user car rental order
 *
 * @author Gandalf
 * @since 2022-06-11 01:13
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
public class RentalOrder extends BaseDO {
    private static final long serialVersionUID = 5631668250113918400L;

    private Long userId;

    private Long carId;

    private Long gmtStartTime;

    private Long gmtEndTime;

    /**
     * order status: 0 - ,1 - valid
     */
    private Integer orderStatus;
}

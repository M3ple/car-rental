package com.car.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.car.entity.base.BaseDO;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * car rental order
 *
 * @author Gandalf
 * @since 2022-06-11 00:58
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
@TableName("car_order")
public class Order extends BaseDO {

    private static final long serialVersionUID = 7469394723197814144L;

    /**
     * car id
     */
    private Long carId;
    /**
     * user id
     */
    private String uid;

    /**
     * order status
     */
    private Integer orderStatus;

    /**
     * rent start time
     */
    private Long gmtStart;

    /**
     * rent end time
     */
    private Long gmtEnd;

}

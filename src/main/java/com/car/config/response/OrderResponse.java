package com.car.config.response;

import com.car.entity.Car;
import lombok.*;

import java.io.Serializable;

/**
 * car rental order response
 *
 * @author Gandalf
 * @since 2022-06-11 00:58
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderResponse implements Serializable {

    private static final long serialVersionUID = 7469394723197814144L;

    /**
     * order id
     */
    private String id;

    /**
     * car id
     */
    private Car car;

    /**
     * order status
     */
    private Integer orderStatus;

    /**
     * rent start time
     */
    private String gmtStart;

    /**
     * rent end time
     */
    private String gmtEnd;

}

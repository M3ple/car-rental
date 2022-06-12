package com.car.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * car book request entity
 *
 * @author Gandalf
 * @since 2022-06-11 17:47
 */
@Getter
@Setter
@ToString
public class BookCarRequest implements Serializable {
    private static final long serialVersionUID = 539651727993213717L;

    /**
     * car type
     */
    private Integer carType;

    /**
     * user id
     */
    private String uid;

    /**
     * rent start time
     */
    private Long gmtStart;

    /**
     * rent end time
     */
    private Long gmtEnd;
}

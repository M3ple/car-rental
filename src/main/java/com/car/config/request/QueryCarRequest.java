package com.car.config.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * car query request
 *
 * @author Gandalf
 * @since 2022-06-12 16:35
 */
@Getter
@Setter
@ToString
public class QueryCarRequest implements Serializable {
    private static final long serialVersionUID = 775045819271601218L;

    /**
     * car ids
     */
   private List<Long> carIds;
    /**
     * car type
     */
   private Integer type;
    /**
     * start time
     */
   private String startTime;
    /**
     * end time
     */
   private String endTime;

}

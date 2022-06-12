package com.car.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.car.entity.base.BaseDO;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * car
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
@TableName("car")
public class Car extends BaseDO {

    private static final long serialVersionUID = 7469394723197814144L;

    /**
     * car type
     * @see com.car.entity.enums.CarTypeEnum
     */
    private Integer type;

}

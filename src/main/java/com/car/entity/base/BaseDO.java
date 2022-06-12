package com.car.entity.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * base DO
 *
 * @author Gandalf
 * @since 2022-06-11 01:27
 */
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BaseDO implements Serializable {

    private static final long serialVersionUID = -675588402251009953L;

    /**
     * data id
     */
    @TableId(type = IdType.INPUT)
    private Long id;

    /**
     * data status: 0 - logical deleted, 1 - valid
     */
    private Integer status;

    /**
     * modify timestamp
     */
    private Long gmtCreate = System.currentTimeMillis();

    /**
     * modify timestamp
     */
    private Long gmtModify = System.currentTimeMillis();
}

package com.car.entity.exception;

import com.car.entity.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * exception
 *
 * @author Gandalf
 * @since 2022-06-11 18:04
 */
@Setter
@Getter
@AllArgsConstructor
@ToString
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = -6659095562717491132L;

    ExceptionEnum exceptionEnum;

    public static BaseException of(ExceptionEnum exceptionEnum){
        return new BaseException(exceptionEnum);
    }
}

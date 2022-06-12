package com.car.controller.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.car.entity.enums.ExceptionEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * common response entity
 *
 * @author Gandalf
 * @since 2022/6/11
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Response<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 使用 ordinal 参数指定字段的顺序
     */
    @JSONField(ordinal = 0)
    private boolean success = true;

    @JSONField(ordinal = 1)
    private String errorCode;

    @JSONField(ordinal = 2)
    private String errorInfo;

    @JSONField(ordinal = 3)
    private T data;

    public static <T> Response<T> success(T data) {
		Response<T> response = new Response<T>();
		response.setSuccess(true);
		response.setErrorCode(ExceptionEnum.OK.getCode());
		response.setErrorInfo(ExceptionEnum.OK.getMessage());
        response.setData(data);
        return response;
    }

    public static <T> Response<T> fail(ExceptionEnum exceptionEnum) {
        Response<T> response = new Response<T>();
        response.setSuccess(false);
        response.setErrorCode(exceptionEnum.getCode());
        response.setErrorInfo(exceptionEnum.getMessage());
        return response;
    }
}

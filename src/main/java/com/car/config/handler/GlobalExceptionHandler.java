package com.car.config.handler;

import com.car.config.response.Response;
import com.car.entity.enums.ExceptionEnum;
import com.car.entity.exception.BaseException;
import com.car.entity.exception.LoginException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * exception handler
 *
 * @author Gandalf
 * @since 2022/6/11
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public Response<Boolean> handlerException(BaseException e) {
        log.error(e.getMessage(), e);
        return Response.fail(e.getExceptionEnum());
    }

    @ExceptionHandler(value = LoginException.class)
    public ModelAndView handlerException(LoginException e) {
        log.error(e.getMessage(), e);
        return new ModelAndView("login");
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Response<Boolean> handlerException(Exception e) {
        log.error(e.getMessage(), e);
        return Response.fail(ExceptionEnum.SYSTEM_ERROR);
    }
}

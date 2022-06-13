package com.car.config;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.car.entity.enums.ExceptionEnum;
import com.car.entity.exception.LoginException;
import com.car.utils.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;

/**
 * login check
 *
 * @author Gandalf
 * @since 2022/6/13
 */
@Slf4j
@Aspect
@Component
public class LoginAspect {

    @Resource(name = "logStatus")
    private ConcurrentHashMap<String, Boolean> logStatus;

    @Pointcut("execution(public * com.car.controller.book..*.*(..))")
    public void controller() {
    }

    @Around("controller()")
    public Object handle(ProceedingJoinPoint joinPoint) throws Throwable {
        String uid = UserUtil.getUid();
        if (StringUtils.isBlank(uid) || !logStatus.getOrDefault(uid, Boolean.FALSE)) {
            throw LoginException.of(ExceptionEnum.PLEASE_LOG_IN);
        }
        return joinPoint.proceed(joinPoint.getArgs());
    }
}

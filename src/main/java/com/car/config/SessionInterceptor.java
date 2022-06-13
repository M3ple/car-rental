package com.car.config;

import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.car.utils.UserUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * handler interceptor
 *
 * @author Gandalf
 * @since 2022/6/13
 */
public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (ArrayUtils.isNotEmpty(cookies)) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("uid") && !StringUtils.isBlank(cookie.getValue())) {
                    UserUtil.setUid(cookie.getValue());
                }
            }
        }
        if (request.getRequestURI().contains("/login")
                || request.getRequestURI().contains("/register")
                || request.getRequestURI().contains("css")
                || request.getRequestURI().contains("js")
        ) {
            return true;
        }
        if (StringUtils.isBlank(UserUtil.getUid())) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws
            Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}

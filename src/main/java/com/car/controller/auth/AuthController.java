package com.car.controller.auth;

import com.car.config.response.Response;
import com.car.entity.User;
import com.car.entity.enums.ExceptionEnum;
import com.car.utils.Assert;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ConcurrentHashMap;

/**
 * auth controller
 *
 * @author Gandalf
 * @since 2022-06-11 13:53
 */
@Controller
public class AuthController {

    @Resource(name = "userRegistry")
    private ConcurrentHashMap<String, User> userRegistry;

    @Resource(name = "logStatus")
    private ConcurrentHashMap<String, Boolean> logStatus;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Response<Boolean> register(HttpServletResponse response, String username, String password) {
        Assert.notBlank(username, ExceptionEnum.PARAM_ILLEGAL);
        Assert.notBlank(password, ExceptionEnum.PARAM_ILLEGAL);
        Assert.isTrue(!userRegistry.containsKey(username), ExceptionEnum.USER_ALREADY_EXISTS);
        User user = User.builder().username(username).password(password).build();
        userRegistry.put(username, user);

        Cookie cookie = new Cookie("uid", username);
        response.addCookie(cookie);

        logStatus.put(username, true);
        return Response.success(true);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Response<Boolean> login(HttpServletResponse response, String username, String password) {
        Assert.notBlank(username, ExceptionEnum.PARAM_ILLEGAL);
        Assert.notBlank(password, ExceptionEnum.PARAM_ILLEGAL);

        User user = userRegistry.get(username);
        Assert.notNull(user, ExceptionEnum.USERNAME_OR_PASSWORD_NOT_CORRECT);
        Assert.isTrue(password.equals(user.getPassword()), ExceptionEnum.USERNAME_OR_PASSWORD_NOT_CORRECT);

        Cookie cookie = new Cookie("uid", username);
        response.addCookie(cookie);

        logStatus.put(username, true);
        return Response.success(true);
    }
}

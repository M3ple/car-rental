package com.car.controller.book;

import com.car.config.response.Response;
import com.car.utils.UserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;

/**
 * common controller
 *
 * @author Gandalf
 * @since 2022-06-11 13:53
 */
@Controller
public class CommonController {

    @Resource(name = "logStatus")
    private ConcurrentHashMap<String, Boolean> logStatus;

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public Response<Boolean> logout() {
        logStatus.put(UserUtil.getUid(), false);
        return Response.success(true);
    }

    @GetMapping("/{path}")
    public ModelAndView path(@PathVariable String path) {
        return new ModelAndView(path);
    }
}

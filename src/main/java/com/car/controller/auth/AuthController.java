package com.car.controller.auth;

import com.car.service.CarRentalService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * auth controller
 *
 * @author Gandalf
 * @since 2022-06-11 13:53
 */
@Controller
@RequestMapping("auth")
public class AuthController {

    @Resource
    private CarRentalService carRentalService;

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView index = new ModelAndView("index");
        return index;
    }

    @GetMapping("/{path}")
    public ModelAndView login(@PathVariable String path) {
        return new ModelAndView(path);
    }


}

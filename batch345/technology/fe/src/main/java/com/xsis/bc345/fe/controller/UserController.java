package com.xsis.bc345.fe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @GetMapping("/user")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/user/index");

        return view;
    }
}

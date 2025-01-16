package com.xsis.bc345.fe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomerController {

    @GetMapping("/customer")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/customer/index");

        return view;
    }
}

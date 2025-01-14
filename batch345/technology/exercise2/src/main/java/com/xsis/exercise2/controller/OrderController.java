package com.xsis.exercise2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderController {
    @GetMapping("/order")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/order/index");
        return view;
    }
}

package com.xsis.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderHistory {
    @GetMapping("/order-history")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("//order-history/index");
        return view;
    }
}

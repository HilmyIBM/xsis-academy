package com.xsis.b345.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderHistory {
     @GetMapping("/OrderHistory")
    public ModelAndView order_history() {
        return new ModelAndView("/OrderHistory/index");
    }   
}

package com.xsis.bc345.fe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderController {
    @GetMapping("order")
    public ModelAndView order() {
        return new ModelAndView("order/index");
    }

    @GetMapping("order/history")
    public ModelAndView orderHistory() {
        return new ModelAndView("order/history");
    }
    
}

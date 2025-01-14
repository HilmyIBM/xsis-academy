package com.xsis.bc345.fe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class OrderHistoryController {
    @GetMapping("/orderHistory")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/orderHistory/index");
        return view;
    }
    
}

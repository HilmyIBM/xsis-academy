package com.xsis.b345.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class product {
    @GetMapping("/product")
    public ModelAndView product(){
        return new ModelAndView("/product/index");
    }
}

package com.xsis.b345.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryController {
    @GetMapping("/category")
    public ModelAndView index(){
        return new ModelAndView("/category/index");
    }
}

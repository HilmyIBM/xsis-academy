package com.xsis.bc345.fe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CategoryController {
    @GetMapping("category")
    public ModelAndView category() {
        return new ModelAndView("category/index");
    }
    
    @GetMapping("category/variant")
    public ModelAndView caregoryVariant() {
        return new ModelAndView("category/variant");
    }

    @GetMapping("category/product")
    public ModelAndView categoryProduct() {
        return new ModelAndView("category/product");
    }
    
    @GetMapping("category/user")
    public ModelAndView categoryUser() {
        return new ModelAndView("category/user");
    }
    
}
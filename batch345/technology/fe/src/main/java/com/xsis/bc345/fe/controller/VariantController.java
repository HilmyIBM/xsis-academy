package com.xsis.bc345.fe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class VariantController {
    @GetMapping("/variant")
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("/variant/index");

        return view;
    }
    
}

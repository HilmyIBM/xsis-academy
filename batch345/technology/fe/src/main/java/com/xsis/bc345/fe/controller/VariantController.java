package com.xsis.bc345.fe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

public class VariantController {
    
    @GetMapping("variant")
    public ModelAndView caregoryVariant() {
        return new ModelAndView("variant/index");
    }
}

package com.xsis.bc345.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/variant")
public class VariantController {

    @GetMapping("")
    public ModelAndView index() {
      return new ModelAndView("master/variant/index");
    }

}

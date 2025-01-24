package com.xsis.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/index");
        return view;
    }

    @GetMapping("/")
    

    @GetMapping("/about")
    public ModelAndView about() {
        ModelAndView view = new ModelAndView("/about");
        return view;
    }

}

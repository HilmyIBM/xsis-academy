package com.xsis.b345.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {
    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView view=new ModelAndView("index");

        return view;
    }

    @GetMapping("/about")
    public ModelAndView about() {
        return new ModelAndView("about");
    }
    
}

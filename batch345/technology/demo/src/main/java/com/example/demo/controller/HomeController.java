package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/")
    public ModelAndView home() {
        // model.addAttribute("message", "Welcome to Spring MVC!");
        return new ModelAndView("/home");
    }

    @GetMapping("/about")
    public ModelAndView about() {
        return new ModelAndView("/about");
    }
}

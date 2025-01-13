package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @GetMapping("/")
    // public String home() {
    //     // model.addAttribute("message", "Welcome to Spring MVC!");
    //     return "home";
    // }
    public ModelAndView home(){
        return new ModelAndView("/home");
    }
    
    @GetMapping("/about")
    // public String about(){
        //     return "about";
        // }
    public ModelAndView about(){
        return new ModelAndView("/about");
    }
}

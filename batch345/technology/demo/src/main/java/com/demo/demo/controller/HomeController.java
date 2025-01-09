package com.demo.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {

    @GetMapping("/")
    public String home (Model model) {
        model.addAttribute("message", "Welcome to Spring MVC");
        return "home";
    }
    
    
}

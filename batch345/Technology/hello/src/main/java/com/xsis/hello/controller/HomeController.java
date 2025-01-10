package com.xsis.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("message", "Welcome to spring");
        return "home";
    }

    @PostMapping("/greet")
    public String greetuser(@RequestParam(defaultValue = "user") String name, Model model){
        model.addAttribute("message", "Hello, "+name +"!");
        return "home";
    }
}

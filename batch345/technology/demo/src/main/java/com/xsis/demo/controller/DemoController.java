package com.xsis.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


@Controller
public class DemoController {
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("message", "Welcome to Spring MVC");
        return "home";
    }
    // @PostMapping("/greet")
    // public String greetUser(@RequestParam("name") String name, Model model) {
    //     //TODO: process POST request
    //     model.addAttribute("message", "hello, " + name + "!");
    //     return "home";
    // }
    
}

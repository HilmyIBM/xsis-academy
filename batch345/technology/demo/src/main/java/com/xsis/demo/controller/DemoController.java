package com.xsis.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
// import ch.qos.logback.core.model.Model;  
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


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

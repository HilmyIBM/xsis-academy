package com.xsis.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xsis.hello.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


// Buat Fullstack untuk FE juga di resources
@Controller
public class HomeController {
    @GetMapping("/home")
    public String displayHome(Model model) {
        // Create a User object and add it to the model
        User user = new User("John Doe", 30);
        model.addAttribute("user", user);
        // Return the name of the view (home.html)
        return "home";
    }

    @GetMapping("/greet2")
    public String displayGreeting(@RequestParam(name = "name", required = false, defaultValue = "Guest") String name,
            Model model) {
        // Add the "name" parameter to the model
        model.addAttribute("name", name);

        // Return the name of the view (greet.html)
        return "greet";
    }

    @PostMapping("/greet")
    public String getUser(@RequestParam("name") String name, Model model) {
        model.addAttribute("message", "Hello, " + name + "!");
        return "home";
    }

}

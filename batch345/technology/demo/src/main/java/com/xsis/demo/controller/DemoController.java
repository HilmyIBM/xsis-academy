package com.xsis.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class DemoController {
    // @GetMapping("/")
    // public String home(Model model){
    //     model.addAttribute("message", "Welcome to Spring MVC");
    //     return "home";
    // }
     @GetMapping("/")
    public ModelAndView home(){
        return new ModelAndView("/home");
    }
    @GetMapping("/about")
    public ModelAndView about(){
        return new ModelAndView("/about");
    }
    // @GetMapping("/about")
    // public String about(Model model) {
    //     model.addAttribute("message", "Welcome to Spring MVC");
    //     return "about";
    // }
    
    // @PostMapping("/greet")
    // public String greetUser(@RequestParam("name") String name, Model model) {
    //     model.addAttribute("message", "hello, " + name + "!");
    //     return "home";
    // }
    
}

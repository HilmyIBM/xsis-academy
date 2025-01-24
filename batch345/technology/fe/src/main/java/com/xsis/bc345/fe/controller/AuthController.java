package com.xsis.bc345.fe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {
    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView view = new ModelAndView("/auth/login");
        view.addObject("title", "Login Pages");
        return view;
    }
}

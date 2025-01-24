package com.xsis.bc345.fe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public ModelAndView index(HttpSession sess) {
        ModelAndView view = new ModelAndView("/index");

        //Session Variables
        sess.setAttribute("coba", "value dari Home Page");

        return view;
    }

    @GetMapping("/about")
    public ModelAndView about() {
        ModelAndView view = new ModelAndView("/about");

        return view;
    }
}

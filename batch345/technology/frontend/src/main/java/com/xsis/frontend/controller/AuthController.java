package com.xsis.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView view = new ModelAndView("auth/login");
        view.addObject("title", "Login Page");

        return view;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        ModelAndView view = new ModelAndView("redirect:/");

        return view;
    }
}

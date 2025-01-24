package com.xsis.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
    @GetMapping("/")
    public ModelAndView index(HttpSession session) {
        ModelAndView view = new ModelAndView("/index");
        view.addObject("txtSession", session.getAttribute("coba"));

        return view;
    }

    @GetMapping("/setSession")
    public ModelAndView setSession(@RequestParam() String txtSession, HttpSession session) {
        session.setAttribute("coba", txtSession);

        return new ModelAndView("redirect:/");
    }

    @GetMapping("/about")
    public ModelAndView about() {
        ModelAndView view = new ModelAndView("/about");
        return view;
    }

}

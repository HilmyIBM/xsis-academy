package com.xsis.b345.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @GetMapping("/")
    public ModelAndView index(HttpSession session) {
        ModelAndView view = new ModelAndView("index");
        view.addObject("txtSesi",session.getAttribute("email"));
        view.addObject("username", session.getAttribute("userName"));
        return view;
    }

    @GetMapping("/about")
    public ModelAndView about() {
        return new ModelAndView("about");
    }

    @GetMapping("/setSesi")
    public ModelAndView setSesi(@RequestParam() String sesi,HttpSession session){
        session.setAttribute("coba", sesi);
        return new ModelAndView("redirect:/");
    }

}

package com.xsis.bc345.fe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public ModelAndView index(HttpSession sess) {
        ModelAndView view = new ModelAndView("/index");

        //Session Variables
        view.addObject("txtSesi", sess.getAttribute("coba"));

        return view;
    }

    @GetMapping("/setSesi")
    public ModelAndView setSesi(@RequestParam() String txtSesi, HttpSession sess) {
        sess.setAttribute("coba", txtSesi);

        return new ModelAndView("redirect:/");
    }

    @GetMapping("/about")
    public ModelAndView about() {
        ModelAndView view = new ModelAndView("/about");

        return view;
    }
}

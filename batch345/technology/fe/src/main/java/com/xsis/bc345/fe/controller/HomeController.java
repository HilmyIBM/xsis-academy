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
        ModelAndView view = new ModelAndView("index");

        view.addObject("txtSess", sess.getAttribute("coba"));

        return view;
    }

    @GetMapping("/setSess")
    public ModelAndView setSesi(@RequestParam() String txtSess, HttpSession sess) {
        sess.setAttribute("coba", txtSess);
        return new ModelAndView("redirect:/");
    }
}
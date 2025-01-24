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
        // sess.setAttribute("coba");
        view.addObject("txtSesi", sess.getAttribute("coba"));
        return view;
    }
    @GetMapping("/setSesi")
    public ModelAndView setSesi(HttpSession sess, @RequestParam() String txtSesi) {
        sess.setAttribute("userEmail", txtSesi);
        sess.setAttribute("userEmail", txtSesi);
        sess.setAttribute("userEmail", txtSesi); 
        ModelAndView view = new ModelAndView("/index");
        // sess.setAttribute("coba");
        view.addObject("txtSesi", sess.getAttribute("coba"));
        return view;
    }
    @GetMapping("/about")
    public ModelAndView about() {
        return new ModelAndView("/about");
    }
}

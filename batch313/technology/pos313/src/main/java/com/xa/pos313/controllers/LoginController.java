package com.xa.pos313.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/")
public class LoginController {
    
    @GetMapping(value="/") //mengacu ke url yang dipanggil
    public ModelAndView login() {
        ModelAndView view = new ModelAndView("/login"); //file html
        return view;
    }

    @GetMapping(value="/home") //mengacu ke url yang dipanggil
    public ModelAndView home(HttpSession sess) {
        ModelAndView view = new ModelAndView("/home"); //file html
        view.addObject("fullname", sess.getAttribute("fullname"));
        return view;
    }
}

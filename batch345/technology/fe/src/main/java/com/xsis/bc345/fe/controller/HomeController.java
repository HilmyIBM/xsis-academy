package com.xsis.bc345.fe.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public ModelAndView home(HttpSession sess){
        ModelAndView view = new ModelAndView("/index");
        view.addObject("txtSesi", sess.getAttribute("coba"));
        // sess.setAttribute("coba", "value dari Home Page");
        return view;
    }

    @GetMapping("/setSesi")
    public ModelAndView setSesi(@RequestParam() String txtSesi, HttpSession sess){
        sess.setAttribute("coba", txtSesi == "" || txtSesi.isBlank() ? null : txtSesi);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/about")
    public ModelAndView about(){
        return new ModelAndView("/about");
    }
}

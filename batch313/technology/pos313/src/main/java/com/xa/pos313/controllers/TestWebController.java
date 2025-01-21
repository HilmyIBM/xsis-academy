package com.xa.pos313.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/web")
public class TestWebController {

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/web/index");
        return view;
    }

}
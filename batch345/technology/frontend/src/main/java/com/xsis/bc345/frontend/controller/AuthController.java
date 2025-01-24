package com.xsis.bc345.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/auth")
public class AuthController {
  
  @GetMapping("/login")
  public ModelAndView login() {
      ModelAndView view = new ModelAndView("auth/login");
      view.addObject("title", "Login");
      return view;
  }
  

}

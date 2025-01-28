package com.xsis.bc345.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class HomeController {
  @GetMapping("/")
  public ModelAndView home(HttpSession sess) {
      ModelAndView view = new ModelAndView("index");
      view.addObject("txtSesi", sess.getAttribute("userName"));

      return view;
  }

  @GetMapping("/setSesi")
  public ModelAndView setSesi(@RequestParam() String sesi, HttpSession sess) {
      sess.setAttribute("coba", sesi);
      return new ModelAndView("redirect:/");
  }
  
  
}

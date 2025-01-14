package com.xsis.bc345.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class TransactionController {
  @GetMapping("/catalog")
  public ModelAndView catalog() {
      return new ModelAndView("transaction/catalog");
  }

  @GetMapping("/history")
  public ModelAndView history() {
      return new ModelAndView("transaction/history");
  }
  
  
}

package com.xsis.bc345.frontend.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/order")
public class OrderController {
  
  @Value("${application.api.url}")
  private String apiUrl;

  @GetMapping("")
  public ModelAndView index() {
      ModelAndView view = new ModelAndView("order/index");

      view.addObject("title", "Product Catalog");
      return view;
  }
}

package com.xsis.bc345.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("customer")
public class CustomerController {
  
  @GetMapping("")
  public String getMethodName(@RequestParam String param) {
      return new String();
  }
  
  
}

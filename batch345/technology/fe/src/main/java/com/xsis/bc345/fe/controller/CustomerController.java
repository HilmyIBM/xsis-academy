package com.xsis.bc345.fe.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    //HTTP Client
    private RestTemplate restTemplate = new RestTemplate();
    
    //API URL
    @Value("${application.api.url}")
    private String apiUrl;

    @GetMapping("")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/customer/index");
        ResponseEntity<CustomerView> apiResponse;

        

        return view;
    }
}

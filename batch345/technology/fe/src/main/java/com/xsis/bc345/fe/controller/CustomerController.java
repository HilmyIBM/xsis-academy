package com.xsis.bc345.fe.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.bc345.fe.models.CustomerView;

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

        try {
            
        } catch (Exception e) {
            // TODO: handle exception
        }

        return view;
    }
}

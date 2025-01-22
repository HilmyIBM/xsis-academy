package com.xsis.b345.frontend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.b345.frontend.models.categoryView;
import com.xsis.b345.frontend.models.customerView;

@Controller
public class customer {
    private RestTemplate restTemplate = new RestTemplate();
    private final String apiUrl = "http://localhost:8080/api/customer";

    @GetMapping("/customer")
    public ModelAndView user() {
        ModelAndView view = new ModelAndView("/customer/index");
        ResponseEntity<customerView[]> apiResponse = null;
        try {
            apiResponse = restTemplate.getForEntity(apiUrl, customerView[].class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                view.addObject("customer", apiResponse.getBody());
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + " : " + apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMSG", e.getMessage());
        }
        return view;
    }
}

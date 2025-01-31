package com.xsis.frontend.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.frontend.model.ProductView;

@Controller
public class OrderController {
    private RestTemplate restTemplate = new RestTemplate();

    @Value("${application.api.url}")
    private String apiUrl;
    
    @GetMapping("/order")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/order/index");
        ResponseEntity<ProductView[]> apiResponse = null;

        try {
            apiResponse = restTemplate.getForEntity(apiUrl +"/product", ProductView[].class);

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                view.addObject("product", apiResponse.getBody());
            } else {
                
                
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        view.addObject("title", "Product Catalog");
        return view;
    }
}

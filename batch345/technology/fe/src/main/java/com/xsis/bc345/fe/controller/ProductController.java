package com.xsis.bc345.fe.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.bc345.fe.models.ProductView;


@Controller
@RequestMapping("/product")
public class ProductController {
    // HTTP Client
    private RestTemplate restTemplate = new RestTemplate();

    // API
    private final String apiUrl="http://localhost:8080/api/product";

    @GetMapping("")
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("/product/index");
        ResponseEntity<ProductView[]> apiResponse = null; 

        try {
            apiResponse = restTemplate.getForEntity(apiUrl, ProductView[].class);

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                ProductView[] data = apiResponse.getBody();
                view.addObject("product", data);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }

        return view;
    }
    
}

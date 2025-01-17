package com.xsis.frontend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.frontend.model.ProductView;

@Controller
@RequestMapping("/product")
public class ProductController {
    private RestTemplate restTemplate = new RestTemplate();
    private final String apiUrl = "http://localhost:8080/api";

    @GetMapping("")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/product/index");
        ResponseEntity<ProductView[]> response = null;

        try {
            response = restTemplate.getForEntity(apiUrl + "/products", ProductView[].class);

            if (response.getStatusCode() == HttpStatus.OK) {
                ProductView[] data = response.getBody();
                view.addObject("product", data);
            } else {
                throw new Exception(response.getStatusCode().toString() + ": " + response.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        return view;
    }
}

package com.xsis.b345.frontend.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.b345.frontend.models.productView;

@Controller
public class order {
    @Value("${application.api.url}")
    private String apiUrl;
    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/order")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/order/catalog");
        ResponseEntity<productView[]> apiResponse = null;
        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "/product", productView[].class);
            if (apiResponse.getStatusCode().is2xxSuccessful()) {
                view.addObject("product", apiResponse.getBody());
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + " : " + apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMSG", e.getMessage());
        }
        return view;
    }
}

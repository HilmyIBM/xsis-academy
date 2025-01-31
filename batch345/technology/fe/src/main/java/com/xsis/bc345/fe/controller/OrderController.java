package com.xsis.bc345.fe.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.bc345.fe.models.ProductView;

@Controller
@RequestMapping("/order")
public class OrderController {
    private RestTemplate restTemplate = new RestTemplate();

    @Value("${application.api.url}")
    private String apiUrl;
    @GetMapping("")
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("order/index");
        ResponseEntity<ProductView[]> apiResponse = null;
        view.addObject("title", "product catalog");
        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "/product", ProductView[].class);
            if(apiResponse.getStatusCode() == HttpStatus.OK){
                view.addObject("products", apiResponse.getBody());
            }else{
                throw new Exception(apiResponse.getStatusCode().toString()+ ": "+ apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        return view;
    }
}

package com.xsis.bc345.fe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.bc345.fe.models.OrderDetailView;
import com.xsis.bc345.fe.models.ProductView;

@Controller
@RequestMapping("/order")
public class OrderController {
        // HTTP Client
    private RestTemplate restTemplate = new RestTemplate();

    // API
    @Value("${application.api.url}")
    private String apiUrl;
    
    @GetMapping("")
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("/order/index");
        ResponseEntity<ProductView[]> apiResponse = null;

        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "/product", ProductView[].class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                view.addObject("products", apiResponse.getBody());
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }

        view.addObject("title", "Product Catalog");
        return view;
    }

    @GetMapping("/cart")
    public ModelAndView cart(List<OrderDetailView> listCart, Integer totalProduct, Double estPrice){
        ModelAndView view = new ModelAndView("/order/cart");
        view.addObject("title", "Order Details");
        view.addObject("totalProduct", totalProduct);
        view.addObject("estPrice", estPrice);
        return view;
    }
}

package com.xsis.bc345.fe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.bc345.fe.models.OrderDetailView;
import com.xsis.bc345.fe.models.ProductView;

import ch.qos.logback.core.model.Model;

@Controller
@RequestMapping("/order")
public class OrderController {
    //HTTP Client
    private RestTemplate restTemplate = new RestTemplate();
    
    //Image Folder Name
    @Value("${application.imageFolder}")
    private String IMG_FOLDER;
    
    //API URL
    @Value("${application.api.url}")
    private String apiUrl;
    
    @GetMapping("")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/order/index");
        ResponseEntity<ProductView[]> apiResponse = null;

        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "/product", ProductView[].class);

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                view.addObject("products", apiResponse.getBody());
            }
            else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }

        view.addObject("title", "Product Catalog");
        view.addObject("IMG_FOLDER", IMG_FOLDER);

        return view;
    }

    @PostMapping("/cart")
    public ModelAndView cart(OrderDetailView[] listCart, Integer totProduct, Double estPrice) {
        ModelAndView view = new ModelAndView("/order/cart");

        view.addObject("title", "Order Details");
        view.addObject("totProduct", totProduct);
        view.addObject("estPrice", estPrice);
        view.addObject("listCart", listCart);

        return view;
    }
}

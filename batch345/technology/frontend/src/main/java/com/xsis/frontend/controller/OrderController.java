package com.xsis.frontend.controller;

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

import com.xsis.frontend.model.ProductView;

@Controller
@RequestMapping("/order")
public class OrderController {
    private RestTemplate restTemplate = new RestTemplate();

    @Value("${application.api.url}")
    private String apiUrl;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @GetMapping("")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/order/index");
        ResponseEntity<ProductView[]> response = null;
        try {
            response = restTemplate.getForEntity(apiUrl + "/products",
                    ProductView[].class);
            if (response.getStatusCode() == HttpStatus.OK) {
                ProductView[] data = response.getBody();
                view.addObject("products", data);
            } else {
                throw new Exception(response.getStatusCode().toString() + ": " + response.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }

        view.addObject("title", "Product Catalog");
        return view;
    }

    @PostMapping("/cart")
    public ModelAndView cart(List<?> listCart, Integer totalProduct, Double estPrice) {
        ModelAndView view = new ModelAndView("/order/cart");

        view.addObject("title", "Order Details");
        view.addObject("totalProduct", totalProduct);
        view.addObject("estPrice", estPrice);
        view.addObject("listCart", listCart);
        return view;
    }
}

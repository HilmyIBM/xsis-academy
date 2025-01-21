package com.xsis.frontend.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.frontend.model.ProductView;

@Controller
@RequestMapping("/product")
public class ProductController {
    private RestTemplate restTemplate = new RestTemplate();

    @Value("${application.api.url}")
    private String apiUrl;

    @GetMapping("")
    public ModelAndView index(String filter) {
        ModelAndView view = new ModelAndView("product/index");
        ResponseEntity<ProductView[]> response = null;

        try {
            if (filter == null || filter.isBlank()) {
                response = restTemplate.getForEntity(apiUrl + "/products", ProductView[].class);
            } else {
                response = restTemplate.getForEntity(apiUrl + "/products/filter/" + filter, ProductView[].class);
            }

            if (response.getStatusCode() == HttpStatus.OK) {
                ProductView[] data = response.getBody();
                view.addObject("product", data);
            } else {
                throw new Exception(response.getStatusCode().toString() + ": " + response.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        view.addObject("filter", filter);
        return view;
    }

    @GetMapping("/{id}")
    public ModelAndView detail(@PathVariable int id) {
        ModelAndView view = new ModelAndView("product/detail");
        ResponseEntity<ProductView> response = null;

        try {
            response = restTemplate.getForEntity(apiUrl + "/products/id/" + id, ProductView.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                ProductView data = response.getBody();
                view.addObject("product", data);
            } else {

            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        view.addObject("title", "Product Detail");

        return view;
    }
}

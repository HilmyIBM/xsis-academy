package com.xsis.bc345.fe.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.bc345.fe.models.CategoryView;
import com.xsis.bc345.fe.models.ProductView;

@Controller
public class ProductController {
    //HTTP Client
    private RestTemplate restTemplate = new RestTemplate();
    
    //API URL
    @Value("${application.api.url}")
    private String apiUrl;

    @GetMapping("/product")
    public ModelAndView index(String filter) {
        ModelAndView view = new ModelAndView("/product/index");
        ResponseEntity<ProductView[]> apiResponse = null;

        try {
            if (filter == null || filter.isBlank()){
                apiResponse = restTemplate.getForEntity(apiUrl + "/product", ProductView[].class);
            }
            else {
                apiResponse = restTemplate.getForEntity(apiUrl + "/product/filter/" + filter, ProductView[].class);
            }

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                view.addObject("product", apiResponse.getBody());
            }
            else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            // TODO: handle exception
            view.addObject("errorMsg", e.getMessage());
        }

        return view;
    }

    @GetMapping("/product/add")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("/product/add");
        //Category List
        ResponseEntity<CategoryView[]> apiCategoryResponse = null;

        try {
            apiCategoryResponse = restTemplate.getForEntity(apiUrl + "/category", CategoryView[].class);

            view.addObject(
                "categories", 
                (apiCategoryResponse.getStatusCode() == HttpStatus.OK) ? apiCategoryResponse.getBody() : new CategoryView()
            );

        } catch (Exception e) {
            // TODO: handle exception
            view.addObject("errorMsg", e.getMessage());
        }

        view.addObject("title", "Add New Product");

        return view;
    }
}

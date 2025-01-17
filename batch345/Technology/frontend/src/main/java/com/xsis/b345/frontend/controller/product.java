package com.xsis.b345.frontend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.b345.frontend.models.categoryView;
import com.xsis.b345.frontend.models.productView;
import com.xsis.b345.frontend.models.variantView;

@Controller
public class product {
    private RestTemplate restTemplate = new RestTemplate();
    private final String apiUrl="http://localhost:8080/api/product";
    private final String apiVariant="http://localhost:8080/api/variant";

    @GetMapping("/product")
    public ModelAndView product(){
        ModelAndView view = new ModelAndView("/product/index");
        ResponseEntity<productView[]> apiResponse=null;
        try {
            apiResponse = restTemplate.getForEntity(apiUrl,productView[].class);
            if (apiResponse.getStatusCode()==HttpStatus.OK) {
                view.addObject("product", apiResponse.getBody());
            } else {
                throw new Exception(apiResponse.getStatusCode().toString()+" : "+apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMSG", e.getMessage());
        }
        return view;
    }

    @GetMapping("/product/add")
    public ModelAndView addData() {
        ModelAndView view= new ModelAndView("/product/add");
        view.addObject("title", "Add new product");
        ResponseEntity<variantView[]> apiResponsevariant=null;
        try {
            apiResponsevariant = restTemplate.getForEntity(apiVariant,variantView[].class);
            if (apiResponsevariant.getStatusCode()==HttpStatus.OK) {
                view.addObject("variant", apiResponsevariant.getBody());
            } else {
                view.addObject("variant", new variantView());
            }
        } catch (Exception e) {
            view.addObject("errorMSG", e.getMessage());
        }

        return view;
    }
}

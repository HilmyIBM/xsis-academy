package com.xsis.b345.frontend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.b345.frontend.models.categoryView;
import com.xsis.b345.frontend.models.variantView;

@Controller
public class variant {
    private RestTemplate restTemplate = new RestTemplate();
    private final String apiUrl="http://localhost:8080/api/variant";
    private final String apiCategory="http://localhost:8080/api/category";

    @GetMapping("/variant")
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("/variant/index");
        ResponseEntity<variantView[]> apiResponse=null;
        ResponseEntity<categoryView[]> apiResponseCategory=null;
        try {
            apiResponse = restTemplate.getForEntity(apiUrl,variantView[].class);
            apiResponseCategory=restTemplate.getForEntity(apiCategory, categoryView[].class);
            if (apiResponse.getStatusCode()==HttpStatus.OK) {
                view.addObject("variant", apiResponse.getBody());
                view.addObject("category", apiResponseCategory.getBody());
            } else {
                throw new Exception(apiResponse.getStatusCode().toString()+" : "+apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMSG", e.getMessage());
        }
        return view;
    }

    @GetMapping("/variant/add")
    public ModelAndView add(){
        ModelAndView view=new ModelAndView("variant/add");
        view.addObject("title", "Add new Variant");
        ResponseEntity<categoryView[]> apiResponseCategory=null;
        try {
            apiResponseCategory = restTemplate.getForEntity(apiCategory,categoryView[].class);
            if (apiResponseCategory.getStatusCode()==HttpStatus.OK) {
                view.addObject("category", apiResponseCategory.getBody());
            } else {
                view.addObject("category", new categoryView());
            }
        } catch (Exception e) {
            view.addObject("errorMSG", e.getMessage());
        }
        return view;
    }

    @PostMapping("/variant/save")
    ResponseEntity<?> save(@ModelAttribute variantView variant){
        ResponseEntity<variantView> apiResponse=null;
        try {
            apiResponse=restTemplate.postForEntity(apiUrl, variant, variantView.class);
            if (apiResponse.getStatusCode()==HttpStatus.CREATED) {
                return new ResponseEntity<variantView>(apiResponse.getBody(),HttpStatus.OK);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString()+" : "+apiResponse.getBody().toString());   
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

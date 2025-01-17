package com.xsis.bc345.fe.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.bc345.fe.models.CategoryView;
import com.xsis.bc345.fe.models.VariantView;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("variant")
public class VariantController {
    //HTTP Client
    private RestTemplate restTemplate = new RestTemplate();

    //API URL
    private final String apiUrl = "http://localhost:8080/api";

    
    @GetMapping("")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/variant/index");
        ResponseEntity<VariantView[]> apiResponse = null;

        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "/variant", VariantView[].class);
            
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                // CategoryView[] data = apiResponse.getBody();
                view.addObject("variant", apiResponse.getBody());
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

    @GetMapping("add")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("/variant/add");
        ResponseEntity<CategoryView[]> apiCategoryResponse = null;

        try {
            apiCategoryResponse = restTemplate.getForEntity(apiUrl + "/category", CategoryView[].class);

            if (apiCategoryResponse.getStatusCode() == HttpStatus.OK) {
                // CategoryView[] data = apiResponse.getBody();
                view.addObject("category", apiCategoryResponse.getBody());
            }
            else {
                view.addObject("category", new CategoryView());
            }

        } catch (Exception e) {
            // TODO: handle exception
        }

        view.addObject("title", "Add New Variant");

        return view;
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@ModelAttribute VariantView variant) {
        ResponseEntity<CategoryView> apiResponse = null;

        try {
            apiResponse = restTemplate.postForEntity(apiUrl, variant, CategoryView.class);

            if(apiResponse.getStatusCode() == HttpStatus.CREATED) {
                return new ResponseEntity<CategoryView>(apiResponse.getBody(), HttpStatus.CREATED);
            }
            else{
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        }
        catch(Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
  
}

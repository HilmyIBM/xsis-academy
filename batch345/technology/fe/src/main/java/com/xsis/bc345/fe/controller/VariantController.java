package com.xsis.bc345.fe.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.bc345.fe.models.CategoryView;
import com.xsis.bc345.fe.models.VariantView;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/variant")
public class VariantController {
    // HTTP Client
    private RestTemplate restTemplate = new RestTemplate();

    // API
    private final String apiUrl="http://localhost:8080/api";

    @GetMapping("")
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("/variant/index");
        ResponseEntity<VariantView[]> apiResponse = null; 

        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "/variant", VariantView[].class);

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                VariantView[] data = apiResponse.getBody();
                view.addObject("variant", data);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }

        return view;
    }

    @GetMapping("add")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("/variant/add");
        ResponseEntity<CategoryView[]> apiResponse = null;
        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "/category", CategoryView[].class);

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                CategoryView[] data = apiResponse.getBody();
                view.addObject("category", data);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }

        view.addObject("title", "Create New Variant");

        return view; 
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@ModelAttribute VariantView variant){
        ResponseEntity<VariantView> apiResponse = null;
        try {
            apiResponse = restTemplate.postForEntity(apiUrl + "/variant", variant , VariantView.class);
            if (apiResponse.getStatusCode() == HttpStatus.CREATED) {
                return new ResponseEntity<VariantView>(apiResponse.getBody(), HttpStatus.OK);
            } else {
                throw new Exception(apiResponse.getBody().toString() + ": " + apiResponse.getBody().toString());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
}

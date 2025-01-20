package com.xsis.bc345.fe.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.bc345.fe.models.VariantView;


@Controller
public class VariantController {
    
        //HTTP Client
    private RestTemplate restTemplate =new RestTemplate();
    
    //API URL
    @Value("${application.api.url}")
    private  String apiUrl; 
    @GetMapping("/variant")
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

    @GetMapping("/add")
    ModelAndView add() {
        ModelAndView view = new ModelAndView("/variant/add");
        view.addObject("title", "Add New Category");

        return view;
    }
    
    @PostMapping("/create")
    ResponseEntity<?> create(@ModelAttribute VariantView variant) {
        ResponseEntity<VariantView> apiResponse = null;

        try {
            apiResponse = restTemplate.postForEntity(apiUrl + "/variant", variant, VariantView.class);

            if(apiResponse.getStatusCode() == HttpStatus.CREATED) {
                return new ResponseEntity<VariantView>(apiResponse.getBody(), HttpStatus.CREATED);
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

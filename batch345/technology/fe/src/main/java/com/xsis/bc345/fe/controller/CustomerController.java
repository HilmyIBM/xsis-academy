package com.xsis.bc345.fe.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.bc345.fe.models.CustomerView;


@Controller
@RequestMapping("/customer")
public class CustomerController {
    // HTTP Client
    private RestTemplate restTemplate = new RestTemplate();

    // API
    @Value("${application.api.url}")
    private String apiUrl;

    @GetMapping("")
    public ModelAndView index(String filter){
        ModelAndView view = new ModelAndView("/customer/index");
        ResponseEntity<CustomerView[]> apiResponse = null; 
        
        try {
            if (filter == null || filter.isBlank()) {
                apiResponse = restTemplate.getForEntity(apiUrl + "/customer", CustomerView[].class);
            }else {
                apiResponse = restTemplate.getForEntity(apiUrl + "/customer/filter/" + filter, CustomerView[].class);
            }

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                CustomerView[] data = apiResponse.getBody();
                view.addObject("customer", data);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        view.addObject("filter", filter);
        
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("/customer/add");
        view.addObject("title", "Create New Customer");
        return view;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@ModelAttribute CustomerView customer){
        ResponseEntity<CustomerView> apiResponse = null;
        try {
            apiResponse = restTemplate.postForEntity(apiUrl + "/customer", customer , CustomerView.class);
            if (apiResponse.getStatusCode() == HttpStatus.CREATED) {
                return new ResponseEntity<CustomerView>(apiResponse.getBody(), HttpStatus.OK);
            } else {
                throw new Exception(apiResponse.getBody().toString() + ": " + apiResponse.getBody().toString());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}

package com.xsis.bc345.fe.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.bc345.fe.models.CustomerView;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("customer")
public class CustomerController {

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${application.api.url}")
    private String apiUrl;

    @GetMapping("")
    public ModelAndView getAllCustomer(String filter) {
        ModelAndView view = new ModelAndView("customer/index");
        ResponseEntity<CustomerView[]> apiResponse = null;
        try {
            if (filter == null || filter.isBlank()) {
                apiResponse = restTemplate.exchange(apiUrl + "customer", HttpMethod.GET, null, CustomerView[].class);
            } else {
                apiResponse = restTemplate.exchange(apiUrl + "customer/filter/" + filter, HttpMethod.GET, null,
                        CustomerView[].class);
            }
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                CustomerView[] data = apiResponse.getBody();
                view.addObject("customers", data);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + " : " + apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errMsg", e.getMessage());
        }
        return view;
    }

    @GetMapping("add")
    public ModelAndView addCustomer() {
        ModelAndView view = new ModelAndView("customer/add");
        view.addObject("title", "Add Customer");
        return view;
    }

    @PostMapping("create")
    public ResponseEntity<?> createCustomer(@ModelAttribute CustomerView data) {
        ResponseEntity<CustomerView> apiResponse = null;
        try {
            apiResponse = restTemplate.exchange(apiUrl + "customer", HttpMethod.POST,
                    new HttpEntity<CustomerView>(data), CustomerView.class);
            if (apiResponse.getStatusCode() == HttpStatus.CREATED) {
                return new ResponseEntity<CustomerView>(apiResponse.getBody(), HttpStatus.CREATED);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + " : " + apiResponse.getBody());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package com.xsis.frontend.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.frontend.model.CustomerView;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    private RestTemplate restTemplate = new RestTemplate();


    @Value("${application.api.url}")
    private String apiUrl;

   @GetMapping("")
    public ModelAndView index(String filter) {
        ModelAndView view = new ModelAndView("customer/index");
        ResponseEntity<CustomerView[]> apiResponse = null;

        try {
            if (filter == null || filter.isBlank()) {
                apiResponse = restTemplate.getForEntity(apiUrl + "/customers", CustomerView[].class);
            } else {
                apiResponse = restTemplate.getForEntity(apiUrl + "/customers/filter/" + filter, CustomerView[].class);
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
}

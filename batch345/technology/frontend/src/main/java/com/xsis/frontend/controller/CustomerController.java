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
        ResponseEntity<CustomerView[]> response = null;

        try {
            if (filter == null || filter.isBlank()) {
                response = restTemplate.getForEntity(apiUrl + "/customers", CustomerView[].class);
            } else {
                response = restTemplate.getForEntity(apiUrl + "/customers/filter/" + filter, CustomerView[].class);
            }

            if (response.getStatusCode() == HttpStatus.OK) {
                CustomerView[] data = response.getBody();
                view.addObject("customer", data);
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
        ModelAndView view = new ModelAndView("customer/detail");
        ResponseEntity<CustomerView> response = null;
        try {
            response = restTemplate.getForEntity(apiUrl + "/customers/id/" + id, CustomerView.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                CustomerView data = response.getBody();
                view.addObject("customer", data);
            } else {
                throw new Exception(response.getStatusCode().toString() + ": " + response.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        view.addObject("title", "User Detail");

        return view;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("customer/add");
        view.addObject("title", "Add New User");
        return view;
    }

}

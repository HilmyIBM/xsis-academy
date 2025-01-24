package com.xsis.bc345.fe.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.bc345.fe.models.CustomerView;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    //HTTP Client
    private RestTemplate restTemplate = new RestTemplate();
    
    //API URL
    @Value("${application.api.url}")
    private String apiUrl;

    @GetMapping("")
    public ModelAndView index(String filter, HttpSession sess) throws Exception {
        ModelAndView view = new ModelAndView("/customer/index");
        ResponseEntity<CustomerView[]> apiResponse = null;

        try {
            if (filter == null || filter.isBlank()) {
                apiResponse = restTemplate.getForEntity(apiUrl + "/customer", CustomerView[].class);
            }
            else {
                apiResponse = restTemplate.getForEntity(apiUrl + "/customer/filter/" + filter, CustomerView[].class);
            }

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                view.addObject("customers", apiResponse.getBody());
            }
            else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        }
        catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }

        return view;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("/customer/add");

        view.addObject("title", "Add New Customer");

        return view;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@ModelAttribute CustomerView data) {
        try {
            ResponseEntity<CustomerView> apiResponse = restTemplate.postForEntity(apiUrl + "/customer/create", data, CustomerView.class);
            
            if (apiResponse.getStatusCode() == HttpStatus.CREATED) {
                return new ResponseEntity<CustomerView>(apiResponse.getBody(), apiResponse.getStatusCode());
            }
            else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        ModelAndView view = new ModelAndView("/customer/edit");

        view.addObject("title", "view");
        view.addObject("id", id);

        return view;
    }

        
    @PostMapping("/edit/{id}")
    public ResponseEntity<?> edit(@ModelAttribute CustomerView data) {
        ModelAndView view = new ModelAndView("/customer/edit");

        try {
            view.addObject("title", "view");

            return new ResponseEntity<CustomerView>(new CustomerView(), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        ModelAndView view = new ModelAndView("/customer/delete");

        view.addObject("title", "Delete Customer");
        view.addObject("id", id);

        return view;
    }

    
}

package com.xsis.bc345.fe.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.bc345.fe.models.CategoryView;
import com.xsis.bc345.fe.models.CustomerView;
import com.xsis.bc345.fe.models.RoleView;
import com.xsis.bc345.fe.models.VariantView;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/customer")
public class CustomerController {
    private RestTemplate restTemplate = new RestTemplate();
    @Value("${application.api.url}")
    private String apiUrl;
    List<RoleView> roles = new ArrayList<>();
    public CustomerController(){
        roles.add(new RoleView("1", "Admin"));
        roles.add(new RoleView("2", "Customer"));
        roles.add(new RoleView("3", "Guest"));
    }

    @GetMapping("")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("customer/index");
        ResponseEntity<CustomerView[]> apiResponse = null;
        try {
            apiResponse = restTemplate.getForEntity(apiUrl+"/customer", CustomerView[].class);

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                view.addObject("customer", apiResponse.getBody());
            }else{
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg",e.getMessage());
        }
        view.addObject("title", "Customer List");
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("/customer/add");
        view.addObject("title", "Add New Customer");
        view.addObject("roles", roles);
        return view;
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@ModelAttribute CustomerView customer, @RequestParam("confirmPassword")String cPassword) {
        ResponseEntity<CustomerView> apiResponse = null;
        try {
            if(!customer.getPassword().equals(cPassword)){
                throw new Exception("Password Does not match");
            }
            apiResponse = restTemplate.postForEntity(apiUrl + "/customer", customer,CustomerView.class);
            if(apiResponse.getStatusCode() == HttpStatus.CREATED){
                return new ResponseEntity<CustomerView>(apiResponse.getBody(), HttpStatus.OK);
            }else{
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

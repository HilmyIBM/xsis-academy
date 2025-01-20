package com.xsis.bc345.frontend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.bc345.frontend.models.CategoryView;
import com.xsis.bc345.frontend.models.CustomerView;


@Controller
@RequestMapping("/customer")
public class CustomerController {

  private RestTemplate restTemplate = new RestTemplate();
  private final String apiUrl = "http://localhost:8080/api/customer";

  
  @GetMapping("")
  public ModelAndView index() {
    ModelAndView view = new ModelAndView("master/customer/index");  
    ResponseEntity<CustomerView[]> apiResponse = null;
    
    try {
      apiResponse = restTemplate.getForEntity(apiUrl, CustomerView[].class);
        if (apiResponse.getStatusCode() == HttpStatus.OK) {
          view.addObject("customer", apiResponse.getBody());
        } else {
          throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
        }
    } catch (Exception e) {
      // TODO: handle exception
      view.addObject("errorMsg", e.getMessage());
    }
    return view;
  }
  
  
}

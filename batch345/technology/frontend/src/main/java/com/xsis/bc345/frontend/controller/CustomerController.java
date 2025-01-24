package com.xsis.bc345.frontend.controller;

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

import com.xsis.bc345.frontend.models.CategoryView;
import com.xsis.bc345.frontend.models.CustomerView;


@Controller
@RequestMapping("/customer")
public class CustomerController {

  private RestTemplate restTemplate = new RestTemplate();

  @Value("${application.api.url}")
  private String apiUrl;

  
  @GetMapping("")
  public ModelAndView index() {
    ModelAndView view = new ModelAndView("master/customer/index");  
    ResponseEntity<CustomerView[]> apiResponse = null;
    
    try {
      apiResponse = restTemplate.getForEntity(apiUrl + "/customer", CustomerView[].class);
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
  
  @GetMapping("/add")
  public ModelAndView add() {
    ModelAndView view = new ModelAndView("master/customer/add");
    view.addObject("title", "Add new Customer");
    return view;
  }

  @PostMapping("/create")
  public ResponseEntity<?> create(@ModelAttribute CustomerView customer) {
    ResponseEntity<CustomerView> apiResponse = null;

    try {
      apiResponse = restTemplate.postForEntity(apiUrl + "/customer", customer, CustomerView.class);

      if (apiResponse.getStatusCode() == HttpStatus.CREATED) {
        return new ResponseEntity<CustomerView>(apiResponse.getBody(), HttpStatus.OK);
      } else {
        throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
      }
    } catch (Exception e) {
      // TODO: handle exception
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  
}

package com.xsis.bc345.frontend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.bc345.frontend.models.ProductView;

@Controller
@RequestMapping("/product")
public class ProductController {
  
   private RestTemplate restTemplate = new RestTemplate();
  private final String apiUrl = "http://localhost:8080/api/product";

  @GetMapping("")
  public ModelAndView index() {
      ModelAndView view = new ModelAndView("master/product/index");
      ResponseEntity<ProductView[]> apiResponse = null;

      try {
        apiResponse = restTemplate.getForEntity(apiUrl, ProductView[].class);
          if (apiResponse.getStatusCode() == HttpStatus.OK) {
            view.addObject("product", apiResponse.getBody());
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

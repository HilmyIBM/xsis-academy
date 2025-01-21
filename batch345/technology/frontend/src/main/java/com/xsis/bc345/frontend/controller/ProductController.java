package com.xsis.bc345.frontend.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.bc345.frontend.models.CategoryView;
import com.xsis.bc345.frontend.models.ProductView;
import com.xsis.bc345.frontend.models.VariantView;

@Controller
@RequestMapping("/product")
public class ProductController {
  
   private RestTemplate restTemplate = new RestTemplate();

  @Value("${application.api.url}")
  private String apiUrl;

  // @GetMapping("")
  // public ModelAndView index() {
  //     ModelAndView view = new ModelAndView("master/product/index");
  //     ResponseEntity<ProductView[]> apiResponse = null;

  //     try {
  //       apiResponse = restTemplate.getForEntity(apiUrl + "/product", ProductView[].class);
  //         if (apiResponse.getStatusCode() == HttpStatus.OK) {
  //           view.addObject("product", apiResponse.getBody());
  //         } else {
  //           throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
  //         }
  //     } catch (Exception e) {
  //       // TODO: handle exception
  //       view.addObject("errorMsg", e.getMessage());
  //     }
  //     return view;
  // }

  @GetMapping("")
  public ModelAndView index() {
      ModelAndView view = new ModelAndView("master/product/index");
      ResponseEntity<ProductView[]> apiResponse = null;

      try {
        apiResponse = restTemplate.getForEntity(apiUrl + "/product/native", ProductView[].class);
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

  @GetMapping("/{id}")
  public ModelAndView detail(@PathVariable int id) {
    ModelAndView view = new ModelAndView("master/product/detail");
    ResponseEntity<ProductView> apiResponse = null;

    try {
      apiResponse = restTemplate.getForEntity(apiUrl + "/product/native/id/" + id, ProductView.class);
      if (apiResponse.getStatusCode() == HttpStatus.OK) {
        view.addObject("product", apiResponse.getBody());
      } else {
        throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
      }
    } catch (Exception e) {
      // TODO: handle 
      view.addObject("errorMsg", e.getMessage());
    }
      view.addObject("title", "Product Detail");
      return view;
  }

  @GetMapping("/add")
  public ModelAndView add() {
      ModelAndView view = new ModelAndView("master/product/add");
      view.addObject("title", "Add new Product");
      ResponseEntity<VariantView[]> apiVariantResponse = null;
      try {
        apiVariantResponse = restTemplate.getForEntity(apiUrl + "/variant", VariantView[].class);
        if (apiVariantResponse.getStatusCode() == HttpStatus.OK) {
          view.addObject("variant", apiVariantResponse.getBody());
        } else {
          throw new Exception(apiVariantResponse.getStatusCode().toString() + ": " + apiVariantResponse.getBody());
        }
      } catch (Exception e) {
        // TODO: handle exception
        view.addObject("errorMsg", e.getMessage());
      }
      return view;
  }

  @PostMapping("/create")
  public ResponseEntity<?> create(@ModelAttribute ProductView product) {
    ResponseEntity<ProductView> apiResponse = null;

    try {
      apiResponse = restTemplate.postForEntity(apiUrl + "/product", product, ProductView.class);

      if (apiResponse.getStatusCode() == HttpStatus.CREATED) {
        return new ResponseEntity<ProductView>(apiResponse.getBody(), HttpStatus.OK);
      } else {
        throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
      }
    } catch (Exception e) {
      // TODO: handle exception
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}

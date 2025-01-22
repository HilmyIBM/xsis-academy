package com.xsis.bc345.frontend.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
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
import com.xsis.bc345.frontend.models.VariantView;


@Controller
@RequestMapping("/variant")
public class VariantController {
    //HTTP Client
    private RestTemplate restTemplate = new RestTemplate();

    //API URL
    @Value("${application.api.url}")
    private String apiUrl;
    // private final String apiUrl = "http://localhost:8080/api/variant";

    

  // @GetMapping("")
  // public ModelAndView index() {
  //     ModelAndView view = new ModelAndView("master/variant/index");
  //     ResponseEntity<VariantView[]> apiResponse = null;
  //     ResponseEntity<CategoryView[]> apiCategoryResponse = null;

  //     try {
  //       apiResponse = restTemplate.getForEntity(apiUrl, VariantView[].class);
  //       apiCategoryResponse = restTemplate.getForEntity(apiCategoryUrl, CategoryView[].class);
  //       if (apiResponse.getStatusCode() == HttpStatus.OK) {
  //         view.addObject("variant", apiResponse.getBody());
  //         view.addObject("category", apiCategoryResponse.getBody());
  //       } else {
  //         throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
  //       }
  //     } catch (Exception e) {
  //       view.addObject("errorMsg", e.getMessage());
  //     }
      
  //     return view;
  // }
 
  @GetMapping("")
  public ModelAndView index() {
      ModelAndView view = new ModelAndView("master/variant/index");
      ResponseEntity<VariantView[]> apiResponse = null;

      try {
        apiResponse = restTemplate.getForEntity(apiUrl + "/variant/native", VariantView[].class);
        if (apiResponse.getStatusCode() == HttpStatus.OK) {
          VariantView[] variantResponse = apiResponse.getBody();
          view.addObject("variant", variantResponse);
        } else {
          throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
        }
      } catch (Exception e) {
        view.addObject("errorMsg", e.getMessage());
      }
      
      return view;
  }

  @GetMapping("/{id}")
  public ModelAndView detail(@PathVariable int id) {
    ModelAndView view = new ModelAndView("master/variant/detail");
    ResponseEntity<VariantView> apiResponse = null;

    try {
      apiResponse = restTemplate.getForEntity(apiUrl + "/variant/native/id/" + id, VariantView.class);
      if (apiResponse.getStatusCode() == HttpStatus.OK) {
        view.addObject("variant", apiResponse.getBody());
      } else {
        throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
      }
    } catch (Exception e) {
      // TODO: handle 
      view.addObject("errorMsg", e.getMessage());
    }
      view.addObject("title", "Variant Detail");
      return view;
  }
  
  @GetMapping("/add")
  public ModelAndView add() {
      ModelAndView view = new ModelAndView("master/variant/add");
      view.addObject("title", "Add new Variant");
      ResponseEntity<CategoryView[]> apiCategoryResponse = null;
      try {
        apiCategoryResponse = restTemplate.getForEntity(apiUrl + "/category", CategoryView[].class);
        if (apiCategoryResponse.getStatusCode() == HttpStatus.OK) {
          view.addObject("category", apiCategoryResponse.getBody());
        } else {
          throw new Exception(apiCategoryResponse.getStatusCode().toString() + ": " + apiCategoryResponse.getBody());
        }
      } catch (Exception e) {
        // TODO: handle exception
        view.addObject("errorMsg", e.getMessage());
      }
      return view;
  }
  
  @GetMapping("/edit/{id}") 
  ModelAndView edit(@PathVariable long id){
    ModelAndView view = new ModelAndView("master/variant/edit");
    ResponseEntity<VariantView> apiResponse = null;
    ResponseEntity<CategoryView[]> apiCategoryResponse = null;

    try {
      apiResponse = restTemplate.getForEntity(apiUrl + "/variant/id/" + id, VariantView.class);
      apiCategoryResponse = restTemplate.getForEntity(apiUrl + "/category", CategoryView[].class);
      if (apiResponse.getStatusCode() == HttpStatus.OK){
        view.addObject("variant", apiResponse.getBody());
        view.addObject("category", apiCategoryResponse.getBody());
      }else{
        throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
      }
    } catch (Exception e) {
      // TODO: handle exception

    }
    return view;
  }

  @PostMapping("/create")
  public ResponseEntity<?> create(@ModelAttribute VariantView variant) {
    ResponseEntity<VariantView> apiResponse = null;

    try {
      apiResponse = restTemplate.postForEntity(apiUrl + "/variant", variant, VariantView.class);

      if (apiResponse.getStatusCode() == HttpStatus.CREATED) {
        return new ResponseEntity<VariantView>(apiResponse.getBody(), HttpStatus.OK);
      } else {
        throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
      }
    } catch (Exception e) {
      // TODO: handle exception
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/update")
  public ResponseEntity<?> update(@ModelAttribute VariantView variant) {
      ResponseEntity<VariantView> apiResponse = null;

      try {
        restTemplate.put(apiUrl + "/variant", variant);
        apiResponse = restTemplate.getForEntity(apiUrl + "/variant/id/" + variant.getId(), VariantView.class);
        if (apiResponse.getStatusCode() == HttpStatus.OK){
          return new ResponseEntity<VariantView>(apiResponse.getBody(), HttpStatus.OK);
        }else{
          throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
        }
      } catch (Exception e) {
        // TODO: handle exception
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
      
  }

  @GetMapping("/delete/{id}")
  public ModelAndView delete(@PathVariable int id) {
      ModelAndView view = new ModelAndView("master/variant/delete");
      view.addObject("id", id);
      view.addObject("title", "Delete Variant");
      return view;
  }

  @PostMapping("/delete/{id}/{userId}")
  public ResponseEntity<?> delete(@PathVariable int id, @PathVariable int userId) {
    ResponseEntity<VariantView> apiResponse = null;

      try {
        apiResponse = restTemplate.exchange(apiUrl + "/variant/delete/" + id + "/" + userId, HttpMethod.DELETE, null, VariantView.class);
        if (apiResponse.getStatusCode() == HttpStatus.OK){
          return new ResponseEntity<VariantView>(apiResponse.getBody(), HttpStatus.OK);
        }else{
          throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
        }
      } catch (Exception e) {
        // TODO: handle exception
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
}

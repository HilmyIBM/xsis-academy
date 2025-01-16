package com.xsis.bc345.frontend.controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.bc345.frontend.models.CategoryView;




@Controller
@RequestMapping("/category")
public class CategoryController {
    //HTTP Client
    private RestTemplate restTemplate = new RestTemplate();

    //API URL
    private final String apiUrl = "http://localhost:8080/api/category";
    

  @GetMapping("")
  public ModelAndView index() {
      ModelAndView view = new ModelAndView("master/category/index");
      ResponseEntity<CategoryView[]> apiResponse = null;

      try {
        apiResponse = restTemplate.getForEntity(apiUrl, CategoryView[].class);
        if (apiResponse.getStatusCode() == HttpStatus.OK) {
          view.addObject("category", apiResponse.getBody());
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
    ModelAndView view = new ModelAndView("master/category/detail");
    ResponseEntity<CategoryView> apiResponse = null;

    try {
      apiResponse = restTemplate.getForEntity(apiUrl + "/id/" + id, CategoryView.class);
      if (apiResponse.getStatusCode() == HttpStatus.OK) {
        view.addObject("category", apiResponse.getBody());
      } else {
        throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
      }
    } catch (Exception e) {
      // TODO: handle 
      view.addObject("errorMsg", e.getMessage());
    }
      view.addObject("title", "Category Detail");
      return view;
  }
  
  @GetMapping("/add")
  public ModelAndView add() {
      ModelAndView view = new ModelAndView("master/category/add");
      view.addObject("title", "Add new Category");
      return view;
  }

  // @PostMapping("/save")
  // public String save(@ModelAttribute CategoryView categoryView) {
  //   if(categoryView.getCategoryName() == ""){
  //     return "Gagal";
  //   }
  //   else {
  //     return "Sukses";
  //   }
  // }

  @PostMapping("/create")
  public ResponseEntity<?> create(@ModelAttribute CategoryView category) {
    ResponseEntity<CategoryView> apiResponse = null;

    try {
      apiResponse = restTemplate.postForEntity(apiUrl, category, CategoryView.class);

      if (apiResponse.getStatusCode() == HttpStatus.CREATED) {
        return new ResponseEntity<CategoryView>(apiResponse.getBody(), HttpStatus.OK);
      } else {
        throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
      }
    } catch (Exception e) {
      // TODO: handle exception
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


  @GetMapping("/edit/{id}") 
  ModelAndView edit(@PathVariable long id){
    ModelAndView view = new ModelAndView("master/category/edit");
    ResponseEntity<CategoryView> apiResponse = null;

    try {
      apiResponse = restTemplate.getForEntity(apiUrl + "/id/" + id, CategoryView.class);
      if (apiResponse.getStatusCode() == HttpStatus.OK){
        view.addObject("category", apiResponse.getBody());
      }else{
        throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
      }
    } catch (Exception e) {
      // TODO: handle exception

    }
    return view;
  }

  @PostMapping("/update")
  public ResponseEntity<?> update(@ModelAttribute CategoryView category) {
      ResponseEntity<CategoryView> apiResponse = null;

      try {
        restTemplate.put(apiUrl, category);
        apiResponse = restTemplate.getForEntity(apiUrl + "/id/" + category.getId(), CategoryView.class);
        if (apiResponse.getStatusCode() == HttpStatus.OK){
          return new ResponseEntity<CategoryView>(apiResponse.getBody(), HttpStatus.OK);
        }else{
          throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
        }
      } catch (Exception e) {
        // TODO: handle exception
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
  
}

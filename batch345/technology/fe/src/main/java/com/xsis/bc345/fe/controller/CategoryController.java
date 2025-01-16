package com.xsis.bc345.fe.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.bc345.fe.models.CategoryView;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/category")
public class CategoryController {
    // HTTP Client
    private RestTemplate restTemplate = new RestTemplate();
    
    // API Url
    private final String apiUrl = "http://localhost:8080/api/category";
    
    // private List <Category> data = new ArrayList<Category>();


    // CategoryController(){
        
                // data.add(new Category());
                // data.get(0).setId(1);
                // data.get(0).setName("Makanan");
                // data.get(0).setDescription("Kategori Makanan");
                // data.get(0).setDeleted(false);
                // data.get(0).setCreateBy(1);
                // data.get(0).setCreateDate(LocalDateTime.now());
                
                
                // data.add(new Category());
                // data.get(1).setId(2);
                // data.get(1).setName("Obat");
                // data.get(1).setDescription("Kategori Obat");
                // data.get(1).setDeleted(false);
                // data.get(1).setCreateBy(2);
                // data.get(1).setCreateDate(LocalDateTime.now());

    // }

    @GetMapping("")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/category/index");

        ResponseEntity<CategoryView[]> apiResponse = null;

        try {
            apiResponse = restTemplate.getForEntity(apiUrl, CategoryView[].class);
            
            if (apiResponse.getStatusCode() == HttpStatus.OK){
                CategoryView[] data = apiResponse.getBody();
                view.addObject("category", data);
                // view.addObject("data", apiResponse.getBody());  ---ini juga bisa
            } else {
                throw new Exception(apiResponse.getStatusCode().toString()+": "+apiResponse.getBody());
            }
            
        } catch (Exception e) {
            // TODO: handle exception
            view.addObject("errorMsg", e.getMessage());
        }

        return view;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("/category/add");

        ResponseEntity<CategoryView> apiResponse = null;

        try {
            apiResponse = restTemplate.getForEntity(apiUrl+"/add/", CategoryView.class);
            
            if (apiResponse.getStatusCode() == HttpStatus.OK){
                CategoryView data = apiResponse.getBody();
                view.addObject("category", data);
                // view.addObject("data", apiResponse.getBody());  ---ini juga bisa
            } else {
                throw new Exception(apiResponse.getStatusCode().toString()+": "+apiResponse.getBody());
            }
            
        } catch (Exception e) {
            // TODO: handle exception
            view.addObject("errorMsg", e.getMessage());
        }
        
        view.addObject("Category Detail");

   
        return view;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@ModelAttribute CategoryView category){
        ModelAndView view = new ModelAndView("/category/create");

        ResponseEntity<CategoryView> apiResponse = null;

        try {
            apiResponse = restTemplate.postForEntity(apiUrl, category ,CategoryView.class);
            
            if (apiResponse.getStatusCode() == HttpStatus.OK){
                CategoryView data = apiResponse.getBody();
                view.addObject("category", data);
                // view.addObject("data", apiResponse.getBody());  ---ini juga bisa
            return new ResponseEntity<CategoryView>(data,HttpStatus.CREATED);

            } else {
                throw new Exception(apiResponse.getStatusCode().toString()+": "+apiResponse.getBody());
            }
            
        } catch (Exception e) {
            // TODO: handle exception
            view.addObject("errorMsg", e.getMessage());
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        // view.addObject("Category Detail");


        
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
    ModelAndView view = new ModelAndView("/category/edit");

    ResponseEntity<CategoryView> apiResponse = null;

    try {
        apiResponse = restTemplate.getForEntity(apiUrl + "/id/" + id, CategoryView.class);

        if (apiResponse.getStatusCode() == HttpStatus.OK) {
            CategoryView category = apiResponse.getBody();
            view.addObject("title", "Edit Category");
            view.addObject("category", category);
        } else {
            throw new Exception("Error: " + apiResponse.getStatusCode());
        }
    } catch (Exception e) {
        view.addObject("errorMsg", e.getMessage());
    }

    return view;
}

    

    @GetMapping("/{id}")
    public ModelAndView detail(@PathVariable int id) {
        ModelAndView view = new ModelAndView("/category/detail");

        ResponseEntity<CategoryView> apiResponse = null;

        try {
            apiResponse = restTemplate.getForEntity(apiUrl+"/id/"+id, CategoryView.class);
            
            if (apiResponse.getStatusCode() == HttpStatus.OK){
                CategoryView data = apiResponse.getBody();
                view.addObject("category", data);
                // view.addObject("data", apiResponse.getBody());  ---ini juga bisa
            } else {
                throw new Exception(apiResponse.getStatusCode().toString()+": "+apiResponse.getBody());
            }
            
        } catch (Exception e) {
            // TODO: handle exception
            view.addObject("errorMsg", e.getMessage());
        }
        
        view.addObject("Category Detail");

        return view;
    }

    @PostMapping("/update")
    public ResponseEntity update(@ModelAttribute CategoryView category) throws Exception {
        //TODO: process POST request
        ResponseEntity<CategoryView> apiResponse = null;
        
        try {
            restTemplate.put(apiUrl, category);
            apiResponse = restTemplate.getForEntity(apiUrl+"/id/"+category.getId(), CategoryView.class);

            if(apiResponse.getStatusCode()==HttpStatus.OK){
                return new ResponseEntity<CategoryView>(apiResponse.getBody(), HttpStatus.OK);
            } else {

                throw new Exception(apiResponse.getStatusCode().toString() +": " +apiResponse.getBody());
            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
}

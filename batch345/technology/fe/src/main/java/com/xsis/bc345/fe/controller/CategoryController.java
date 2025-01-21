package com.xsis.bc345.fe.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.bc345.fe.models.CategoryView;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;





@Controller
@RequestMapping("/category")
public class CategoryController {
    //http client
    private RestTemplate restTemplate = new RestTemplate();
    //api url
    // private  final String apiUrl = "http://localhost:8080/api/category";
    @Value("${application.api.url}")
    private String apiUrl;
    
    @GetMapping("")
    public ModelAndView index(String filter) {
        ModelAndView view = new ModelAndView("category/index");
        ResponseEntity<CategoryView[]> apiResponse = null;
        try {
            
            if(filter == null || filter.isBlank()){
                apiResponse = restTemplate.getForEntity(apiUrl + "/category", CategoryView[].class);
            }else{
                apiResponse = restTemplate.getForEntity(apiUrl+ "/category/filter/" + filter, CategoryView[].class);
            }
            if(apiResponse.getStatusCode() == HttpStatus.OK){
                CategoryView[] data = apiResponse.getBody();
                view.addObject("category", data);
            }else{
                throw new Exception(apiResponse.getStatusCode().toString()+ ": "+ apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        view.addObject("filter", filter);
        return view;
    }
    @GetMapping("/{id}")
    public ModelAndView detail(@PathVariable int id) {
        ModelAndView view = new ModelAndView("category/detail");
        ResponseEntity<CategoryView> apiResponse = null;
        try {
            apiResponse = restTemplate.getForEntity(apiUrl +"/category/id/" + id, CategoryView.class);
            if(apiResponse.getStatusCode() == HttpStatus.OK){
                view.addObject("title", "Detail Category");
                view.addObject("category", apiResponse.getBody());
            }else{
                throw new Exception(apiResponse.getStatusCode().toString() + " : " + apiResponse.getBody() + "  HALOOOOOOOOOOOOOOOOOOOOOO INI ERRORRRRR");
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        return view;
    }
    
    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("/category/add");
        view.addObject("title", "Add New Category");
        return view;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@ModelAttribute CategoryView category) {
        ResponseEntity<CategoryView> apiResponse = null;
        try {
            apiResponse = restTemplate.postForEntity(apiUrl + "/category", category,CategoryView.class);
            if(apiResponse.getStatusCode() == HttpStatus.CREATED){
                return new ResponseEntity<CategoryView>(apiResponse.getBody(), HttpStatus.OK);
            }else{
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @PostMapping("/save")
    public String save(@ModelAttribute CategoryView category){
        String result = "sukses";
        if(category.getCategoryName() == ""){
            return "gagal";
        }
        return result;
    }
    @GetMapping("/edit/{id}")
    ModelAndView edit(@PathVariable int id){
        ModelAndView view = new ModelAndView("/category/edit");
        ResponseEntity<CategoryView> apiResponse = null;
        try {
            apiResponse = restTemplate.getForEntity(apiUrl +"/category/id/" + id, CategoryView.class);
            if(apiResponse.getStatusCode() == HttpStatus.OK){
                view.addObject("title", "Edit Category");
                view.addObject("category", apiResponse.getBody());
            }else{
                throw new Exception(apiResponse.getStatusCode().toString() + " : " + apiResponse.getBody() + "  HALOOOOOOOOOOOOOOOOOOOOOO INI ERRORRRRR");
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        return view;
    }
    @PostMapping("/update")
    public ResponseEntity<?> update(@ModelAttribute CategoryView category) {
        ResponseEntity<CategoryView> apiResponse = null;
        try {
            restTemplate.put(apiUrl + "/category", category);
            apiResponse = restTemplate.getForEntity(apiUrl +"/category/id/" + category.getId(), CategoryView.class);
            if(category.getCategoryName() == ""){

            }
            if(apiResponse.getStatusCode() == HttpStatus.OK){
                return new ResponseEntity<CategoryView>(apiResponse.getBody(), HttpStatus.OK);
            }else{
                throw new Exception(apiResponse.getStatusCode().toString() + ": "+ apiResponse.getBody().toString());
            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/delete/{id}")
    ModelAndView delete(@PathVariable int id){
        ModelAndView view = new ModelAndView("/category/delete");
        ResponseEntity<CategoryView> apiResponse = null;
        try {
            apiResponse = restTemplate.getForEntity(apiUrl +"/category/id/" + id, CategoryView.class);
            if(apiResponse.getStatusCode() == HttpStatus.OK){
                view.addObject("title", "Delete Category");
                view.addObject("category", apiResponse.getBody());
            }else{
                throw new Exception(apiResponse.getStatusCode().toString() + " : " + apiResponse.getBody() + "  HALOOOOOOOOOOOOOOOOOOOOOO INI ERRORRRRR");
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        return view;
    }
    @PostMapping("/delete-confirm")
    public ResponseEntity<?> deleteConfirm(@ModelAttribute CategoryView category) {
        ResponseEntity<CategoryView> apiResponse = null;
        System.out.println("haloo masuky");
        try {
            System.out.println("try");
            // restTemplate.delete(apiUrl+ "/delete/" + category.getId() + "/3"); 
            apiResponse = restTemplate.exchange(apiUrl+ "/category/delete/" + category.getId() + "/3", HttpMethod.DELETE, new HttpEntity<CategoryView>(category), CategoryView.class);
            if(apiResponse.getStatusCode() == HttpStatus.OK){
                System.out.println("if");
                return new ResponseEntity<CategoryView>(apiResponse.getBody(), HttpStatus.OK);
            }else{
                System.out.println("else");
                throw new Exception(apiResponse.getStatusCode().toString() + ": "+ apiResponse.getBody().toString());
            }
        } catch (Exception e) {
            System.out.println("catch");
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}

package com.xsis.bc345.fe.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
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

import com.xsis.bc345.fe.models.CategoryView;



@Controller
@RequestMapping("/category")
public class CategoryController {
    //HTTP Client
    private RestTemplate restTemplate = new RestTemplate();
    
    //API URL
    @Value("${application.api.url}")
    // private final String apiUrl = "http://localhost:8080/api/category";
    private String apiUrl;

    @GetMapping("")
    ModelAndView index(String filter) {
        ModelAndView view = new ModelAndView("/category/index");
        ResponseEntity<CategoryView[]> apiResponse = null;

        try {
            if (filter == null || filter.isBlank()) {
                apiResponse = restTemplate.getForEntity(apiUrl + "/category", CategoryView[].class);
            }
            else {
                apiResponse = restTemplate.getForEntity(apiUrl + "/category/filter/" + filter.trim(), CategoryView[].class);
            }
            
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                // CategoryView[] data = apiResponse.getBody();
                view.addObject("category", apiResponse.getBody());
            }
            else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            // TODO: handle exception
            view.addObject("errorMsg", e.getMessage());
        }

        view.addObject("filter", filter);
        
        return view;
    }

    @GetMapping("/{id}")
    public ModelAndView detail(@PathVariable int id) {
        ModelAndView view = new ModelAndView("/category/detail");
        ResponseEntity<CategoryView> apiResponse = null;

        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "/id/" + id, CategoryView.class);

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                // CategoryView[] data = apiResponse.getBody();
                view.addObject("category", apiResponse.getBody());
            }
            else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            // TODO: handle exception
            view.addObject("errorMsg", e.getMessage());
        }
        
        view.addObject("title", "Category Detail");

        return view;
    }

    @GetMapping("/add")
    ModelAndView add() {
        ModelAndView view = new ModelAndView("/category/add");
        view.addObject("title", "Add New Category");
        view.addObject("nama", "Nama Saya Budi");

        return view;
    }
    
    @PostMapping("/create")
    ResponseEntity<?> create(@ModelAttribute CategoryView category) {
        ResponseEntity<CategoryView> apiResponse = null;

        try {
            apiResponse = restTemplate.postForEntity(apiUrl, category, CategoryView.class);

            if(apiResponse.getStatusCode() == HttpStatus.CREATED) {
                return new ResponseEntity<CategoryView>(apiResponse.getBody(), HttpStatus.CREATED);
            }
            else{
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        }
        catch(Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/edit/{id}")
    ModelAndView edit(@PathVariable int id) {
        ModelAndView view = new ModelAndView("/category/edit");
        ResponseEntity<CategoryView> apiResponse = null;

        try {
            // Get Category data by requested Category ID
            apiResponse = restTemplate.getForEntity(apiUrl + "/id/" + id, CategoryView.class);

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                view.addObject("category", apiResponse.getBody());
            }
            else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            // TODO: handle exception
            view.addObject("errorMsg", e.getMessage());
        }

        view.addObject("title", "Edit Category");

        return view;
    }

    @PostMapping("/update")
    ResponseEntity<?> update(@ModelAttribute CategoryView category) {
        ResponseEntity<CategoryView> apiResponse = null;

        try {
            // restTemplate.put(apiUrl, category);
            // apiResponse = restTemplate.getForEntity(apiUrl + "/id/" + category.getId(), CategoryView.class);
            apiResponse = restTemplate.exchange(apiUrl, HttpMethod.PUT, new HttpEntity<CategoryView>(category), CategoryView.class);

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<CategoryView>(apiResponse.getBody(), HttpStatus.OK);
            }
            else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " +  apiResponse.getBody().toString());
            }
        }
        catch(Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        ModelAndView view = new ModelAndView("/category/delete");

        view.addObject("id", id);
        view.addObject("title", "Delete Category");

        return view;
    }

    @PostMapping("/delete/{id}/{userId}")
    public ResponseEntity<?> delete(@PathVariable int id, @PathVariable int userId) {
        ResponseEntity<CategoryView> apiResponse = null;
        CategoryView category = new CategoryView();

        category.setId(id);
        category.setUpdateBy(userId);

        try {
            // restTemplate.delete(apiUrl + "/delete/" + id + "/" + userId);
            apiResponse = restTemplate.exchange(
                apiUrl + "/delete/" + id + "/" + userId,
                HttpMethod.DELETE,
                new HttpEntity<CategoryView>(category),
                CategoryView.class
            );

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<CategoryView>(apiResponse.getBody(), HttpStatus.OK);
            }
            else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " +  apiResponse.getBody().toString());
            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

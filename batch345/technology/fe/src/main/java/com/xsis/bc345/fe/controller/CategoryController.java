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
    // HTTP Client
    private RestTemplate restTemplate = new RestTemplate();

    // API
    @Value("${application.api.url}")
    private String apiUrl;
    // private final String apiUrl="http://localhost:8080/api/category";

    // private List<CategoryView> data;

    @GetMapping("")
    public ModelAndView index(String filter){
        ModelAndView view = new ModelAndView("/category/index");
        ResponseEntity<CategoryView[]> apiResponse = null; 

        /*data = new ArrayList<Category>();
        
        data.add(new Category());
        data.get(0).setId(1);
        data.get(0).setName("Makanan");
        data.get(0).setDescription("Kategori Makanan");
        data.get(0).setDeleted(false);
        data.get(0).setCreateBy(1);
        data.get(0).setCreateDate(LocalDateTime.now());

        data.add(new Category());
        data.get(1).setId(2);
        data.get(1).setName("Obat");
        data.get(1).setDescription("Kategori Obat-obatan");
        data.get(1).setDeleted(false);
        data.get(1).setCreateBy(1);
        data.get(1).setCreateDate(LocalDateTime.now());

        view.addObject("category", data);*/ 
        
        try {
            if (filter == null || filter.isBlank()) {
                apiResponse = restTemplate.getForEntity(apiUrl + "/category", CategoryView[].class);
            }else {
                apiResponse = restTemplate.getForEntity(apiUrl + "/category/filter/" + filter, CategoryView[].class);
            }

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                CategoryView[] data = apiResponse.getBody();
                view.addObject("category", data);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        view.addObject("filter", filter);
        
        return view;
    }

    @GetMapping("/{id}")
    public ModelAndView detail(@PathVariable int id){
        ModelAndView view = new ModelAndView("/category/detail");
        ResponseEntity<CategoryView> apiResponse = null;

        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "/category/id/" + id , CategoryView.class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                CategoryView data = apiResponse.getBody();
                view.addObject("category", data);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        
        view.addObject("title", "Category Detail");
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("/category/add");
        view.addObject("title", "Create New Category");
        return view;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@ModelAttribute CategoryView category){
        ResponseEntity<CategoryView> apiResponse = null;
        try {
            apiResponse = restTemplate.postForEntity(apiUrl + "/category", category , CategoryView.class);
            if (apiResponse.getStatusCode() == HttpStatus.CREATED) {
                return new ResponseEntity<CategoryView>(apiResponse.getBody(), HttpStatus.OK);
            } else {
                throw new Exception(apiResponse.getBody().toString() + ": " + apiResponse.getBody().toString());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/edit/{id}")
    ModelAndView edit(@PathVariable int id) {
        ModelAndView view = new ModelAndView("/category/edit");
        ResponseEntity<CategoryView> apiResponse = null;

        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "/category/id/" + id , CategoryView.class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                CategoryView data = apiResponse.getBody();
                view.addObject("category", data);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }

        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        
        view.addObject("title", "Edit Category");
        
        return view;
    }

    @PostMapping("/update")
    ResponseEntity<?> update(@ModelAttribute CategoryView category){
        ResponseEntity<CategoryView> apiResponse = null;

        try {
            restTemplate.put(apiUrl, category);
            apiResponse = restTemplate.getForEntity(apiUrl + "/category/id/" + category.getId() , CategoryView.class);

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<CategoryView>(apiResponse.getBody(), HttpStatus.OK);
            } else {
                throw new Exception(apiResponse.getBody().toString() + ": " + apiResponse.getBody().toString());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable int id){
        ModelAndView view = new ModelAndView("/category/delete");
        view.addObject("id", id);
        view.addObject("title", "Delete Confirmation");
        return view;
    }

    @PostMapping("/delete/{id}/{userId}")
    ModelAndView delete(@PathVariable int id, @PathVariable int userId) {
        ModelAndView view = new ModelAndView("/category/delete");
        ResponseEntity<CategoryView> apiResponse = null;
        CategoryView category = new CategoryView();

        category.setId(id);
        category.setUpdateBy(userId);

        try {
            apiResponse = restTemplate.exchange(
                apiUrl + "/category/delete/" + id + "/" + userId, 
                HttpMethod.DELETE, new HttpEntity<CategoryView>(category), 
                CategoryView.class
            );
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                CategoryView data = apiResponse.getBody();
                view.addObject("category", data);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }

        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        
        view.addObject("title", "Delete Confirmation");
        
        return view;
    }
}

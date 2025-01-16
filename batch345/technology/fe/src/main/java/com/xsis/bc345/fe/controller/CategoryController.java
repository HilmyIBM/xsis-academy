package com.xsis.bc345.fe.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import com.xsis.bc345.fe.models.CategoryView;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("category")
public class CategoryController {
    // private List<CategoryView> data;

    // HTTP Client
    private RestTemplate restTemplate = new RestTemplate();

    // API URL
    private final String apiUrl = "http://localhost:8080/api/category";

    // public CategoryController(List<Category> data) {
    // // Preventing duplication (Add the data Category for make the 'edit' method
    // can
    // // Edit)
    // data = new ArrayList<Category>();
    // data.add(new Category());
    // data.get(0).setId(1);
    // data.get(0).setName("Makanan");
    // data.get(0).setDescription("Kategori Makanan");
    // ;
    // data.get(0).setDeleted(false);
    // data.get(0).setCreatedBy(1);
    // data.get(0).setCreateDate(LocalDateTime.now());

    // data.add(new Category());
    // data.get(1).setId(2);
    // data.get(1).setName("Obat");
    // data.get(1).setDescription("Kategori Obat");
    // ;
    // data.get(1).setDeleted(false);
    // data.get(1).setCreatedBy(1);
    // data.get(1).setCreateDate(LocalDateTime.now());
    // }

    @GetMapping("")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("category/index");
        ResponseEntity<CategoryView[]> apiResponse = null;
        try {
            apiResponse = restTemplate.getForEntity(apiUrl, CategoryView[].class);
            System.out.println(apiResponse.getBody());
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

    @GetMapping("{id}")
    public ModelAndView detail(@PathVariable int id) {
        ModelAndView view = new ModelAndView("category/detail");
        ResponseEntity<CategoryView> apiResponse = null;
        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "/id/" + id, CategoryView.class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                view.addObject("category", apiResponse.getBody());
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }

        view.addObject("title", "Category Detail");
        return view;
    }

    @GetMapping("add")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("category/add");
        view.addObject("title", "Add New Category");
        view.addObject("nama", "Nama saya budi");
        return view;
    }

    @PostMapping("save")
    public ResponseEntity<?> save(@ModelAttribute CategoryView category) {
        ResponseEntity<CategoryView> apiResponse = null;
        try {
            apiResponse = restTemplate.postForEntity(apiUrl, category,CategoryView.class);
            if (apiResponse.getStatusCode() == HttpStatus.CREATED) {
                return new ResponseEntity<CategoryView>(apiResponse.getBody(), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<String>(apiResponse.getBody().toString() + ": ", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    @PostMapping("update")
    public ResponseEntity<?> update(@ModelAttribute CategoryView category) {
        ResponseEntity<CategoryView> apiResponse = null;
        try {
            restTemplate.put(apiUrl, category);
            apiResponse = restTemplate.getForEntity(apiUrl + "/id/" + category.getId(), CategoryView.class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<CategoryView>(apiResponse.getBody(), HttpStatus.OK);
            } else {
                return new ResponseEntity<String>(apiResponse.getBody().toString() + ": ", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        ModelAndView view = new ModelAndView("category/edit");
        ResponseEntity<CategoryView> apiResponse = null;

        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "/id/" + id, CategoryView.class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                view.addObject("category", apiResponse.getBody());
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }

        view.addObject("title", "Edit Category");
        return view;
    }
}
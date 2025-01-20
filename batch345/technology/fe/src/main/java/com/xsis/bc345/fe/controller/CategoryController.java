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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import com.xsis.bc345.fe.models.CategoryView;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("category")
public class CategoryController {
    // HTTP Client
    private RestTemplate restTemplate = new RestTemplate();

    // API URL
    // private final String apiUrl = "http://localhost:8080/api/category";
    @Value("${application.api.url}")
    private String apiUrl;

    @GetMapping("")
    public ModelAndView index(String filter) {
        ModelAndView view = new ModelAndView("category/index");
        ResponseEntity<CategoryView[]> apiResponse = null;
        try {
            // must null first and then is empty --> if it's isEmpty() first it will showing
            // the console
            if (filter == null || filter.isBlank()) {
                apiResponse = restTemplate.getForEntity(apiUrl, CategoryView[].class);

            } else {
                apiResponse = restTemplate.getForEntity(apiUrl + "/filter/" + filter, CategoryView[].class);
            }

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                view.addObject("category", apiResponse.getBody());
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            view.addObject("errorMsg", e.getMessage());
        }
        return view;
    }

    // Show the modal detail of category
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

    //
    @GetMapping("add")
    public String add(Model model, HttpServletRequest request) {
        // Check if the request is AJAX
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            model.addAttribute("title", "Add New Category");
            return "category/add :: #formAddContent"; // Return only the form fragment
        } else {
            // Redirect to the index page if accessed directly
            return "redirect:/category";
        }
    }

    @PostMapping("save")
    public ResponseEntity<?> save(@ModelAttribute CategoryView category) {
        ResponseEntity<CategoryView> apiResponse = null;
        try {
            apiResponse = restTemplate.postForEntity(apiUrl, category, CategoryView.class);
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

    // Show modal of edit

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

    // Showing the modal of category
    @GetMapping("delete/{id}")
    public ModelAndView deleteCategory(@PathVariable int id) {
        ModelAndView view = new ModelAndView("category/delete");
        view.addObject("id", id);
        view.addObject("title", "Delete Category");
        return view;
    }

    // Delete Category
    @PostMapping("delete/{id}/{userId}")
    public ResponseEntity<?> deleteCategory(@PathVariable int id, @PathVariable int userId) {
        ResponseEntity<CategoryView> apiResponse = null;
        CategoryView category = new CategoryView();

        category.setId(id);
        category.setUpdateBy(userId);
        try {
            // backend endpoint
            restTemplate.delete(apiUrl + "/delete/" + id + "/" + userId);
            // menyesuaikan dengan backend
            apiResponse = restTemplate.exchange(apiUrl + "/delete/" + id + "/" + userId, HttpMethod.DELETE,
                    new HttpEntity<CategoryView>(category), CategoryView.class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<CategoryView>(apiResponse.getBody(), HttpStatus.OK);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody().toString());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
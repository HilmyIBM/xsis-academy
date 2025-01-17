package com.xsis.frontend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.frontend.model.CategoryView;
import com.xsis.frontend.model.VariantView;

@Controller
@RequestMapping("/variant")
public class VariantController {
    private RestTemplate restTemplate = new RestTemplate();

    private final String apiUrl = "http://localhost:8080/api";

    @GetMapping("")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("variant/index");
        ResponseEntity<VariantView[]> response = null;

        try {
            response = restTemplate.getForEntity(apiUrl + "/variants", VariantView[].class);

            if (response.getStatusCode() == HttpStatus.OK) {
                VariantView[] data = response.getBody();
                view.addObject("variant", data);
            } else {
                throw new Exception(response.getStatusCode().toString() + ": " + response.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }

        return view;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("variant/add");
        ResponseEntity<CategoryView[]> resCategory = null;

        try {
            resCategory = restTemplate.getForEntity(apiUrl + "/categories", CategoryView[].class);
            if (resCategory.getStatusCode() == HttpStatus.OK) {
                CategoryView[] data = resCategory.getBody();
                view.addObject("category", data);
            } else {
                throw new Exception(resCategory.getStatusCode().toString() + ": " + resCategory.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        view.addObject("title", "Add New Category");

        return view;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@ModelAttribute VariantView variant) {
        ResponseEntity<VariantView> apiResponse = null;
        try {
            apiResponse = restTemplate.postForEntity(apiUrl + "/variants", variant, VariantView.class);

            if (apiResponse.getStatusCode() == HttpStatus.CREATED) {
                return new ResponseEntity<VariantView>(apiResponse.getBody(), HttpStatus.OK);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

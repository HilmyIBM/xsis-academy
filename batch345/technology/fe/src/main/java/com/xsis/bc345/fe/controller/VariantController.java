package com.xsis.bc345.fe.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.bc345.fe.models.CategoryView;
import com.xsis.bc345.fe.models.VariantView;


@Controller
@RequestMapping("variant")
public class VariantController {

    private RestTemplate restTemplate = new RestTemplate();

    private final String apiUrl = "http://localhost:8080/api/";

    @GetMapping("")
    public ModelAndView caregoryVariant() {
        ModelAndView view = new ModelAndView("variant/index");
        ResponseEntity<VariantView[]> apiResponse = null;
        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "variant", VariantView[].class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                view.addObject("variant", apiResponse.getBody());
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        return view;
    }

    @GetMapping("add")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("variant/add");
        ResponseEntity<CategoryView[]> apiResponse = null;
        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "/category", CategoryView[].class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                view.addObject("category", apiResponse.getBody());
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            view.addObject("errorMsg", e.getMessage());
        }
        view.addObject("title", "Add New Variant");
        view.addObject("nama", "Menambahkan Variant");
        return view;
    }

    // @PostMapping("save")
    // public ResponseEntity<?> save(@ModelAttribute VariantView variantView ) {
    //     ResponseEntity<VariantView>
    //     return entity;
    // }
    
}

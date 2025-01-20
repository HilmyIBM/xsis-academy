package com.xsis.bc345.fe.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.bc345.fe.models.CategoryView;
import com.xsis.bc345.fe.models.VariantView;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/variant")
public class VariantController {
    //HTTP Client
    private RestTemplate restTemplate = new RestTemplate();

    //API URL
    @Value("${application.api.url}")
    // private final String apiUrl = "http://localhost:8080/api";
    private String apiUrl;
    
    @GetMapping("")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/variant/index");
        ResponseEntity<VariantView[]> apiResponse = null;

        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "/variant", VariantView[].class);
            
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                view.addObject("variant", apiResponse.getBody());
            }
            else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }

        } catch (Exception e) {
            // TODO: handle exception
            view.addObject("errorMsg", e.getMessage());
        }

        return view;
    }
 
    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("/variant/add");

        //Category List
        ResponseEntity<CategoryView[]> apiCategoryResponse = null;
        
        try {
            //Get all Categories
            apiCategoryResponse = restTemplate.getForEntity(apiUrl + "/category", CategoryView[].class);
            view.addObject(
                "category", 
                (apiCategoryResponse.getStatusCode() == HttpStatus.OK) ? apiCategoryResponse.getBody() : new CategoryView()
            );
        } catch (Exception e) {
            // TODO: handle exception
            view.addObject("errorMsg", e.getMessage());
        }

        view.addObject("title", "Add New Variant");

        return view;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(Map<String, Object> variant) {
        ResponseEntity<VariantView> apiResponse = null;

        //Mapping Form Data to VariantView
        VariantView data = new VariantView();
        data.setId((int)variant.get("id"));
        data.setName((String)variant.get("name"));
        data.setDescription((String)variant.get("description"));
        data.setCategoryId((int)variant.get("categoryId"));
        data.setCreateBy((int)variant.get("createBy"));

        try {
            apiResponse = restTemplate.postForEntity(apiUrl + "/variant", data, VariantView.class);

            if(apiResponse.getStatusCode() == HttpStatus.CREATED) {
                return new ResponseEntity<VariantView>(apiResponse.getBody(), HttpStatus.CREATED);
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
    public ModelAndView edit(@PathVariable int id) {
        ModelAndView view = new ModelAndView("/variant/edit");

        //Categories List
        ResponseEntity<CategoryView[]> apiCategoryResponse = null;

        //Selected Variant
        ResponseEntity<VariantView> apiVariantResponse = null;

        try {
            //Get All Categories
            apiCategoryResponse = restTemplate.getForEntity(apiUrl + "/category", CategoryView[].class);
            view.addObject(
                "category",
                (apiCategoryResponse.getStatusCode() == HttpStatus.OK) ? apiCategoryResponse.getBody() : new CategoryView()
            );

            //Get the selected Variant
            apiVariantResponse = restTemplate.getForEntity(apiUrl + "/variant/id/" + id, VariantView.class);
            if (apiVariantResponse.getStatusCode() == HttpStatus.OK) {
                view.addObject("variant", apiVariantResponse.getBody());
            }
            else {
                throw new Exception(apiVariantResponse.getStatusCode().toString() + ": " + apiVariantResponse.getBody());
            }
        } catch (Exception e) {
            // TODO: handle exception
            view.addObject("errorMsg", e.getMessage());
        }
        
        view.addObject("title", "Edit Variant");

        return view;
    }
}

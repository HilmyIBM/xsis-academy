package com.xsis.bc345.fe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
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
import com.xsis.bc345.fe.models.VariantView;

@Controller
@RequestMapping("/variant")
public class VariantController {
    // HTTP Client
    private RestTemplate restTemplate = new RestTemplate();

    // API
    @Value("${application.api.url}")
    private String apiUrl;
    // private final String apiUrl="http://localhost:8080/api";

    @GetMapping("")
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("variant/index");
        ResponseEntity<VariantView[]> apiResponse = null; 

        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "/variant", VariantView[].class);

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                VariantView[] data = apiResponse.getBody();
                view.addObject("variant", data);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }

        return view;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("/variant/add");
        ResponseEntity<CategoryView[]> apiResponse = null;
        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "/category", CategoryView[].class);

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                CategoryView[] data = apiResponse.getBody();
                view.addObject("category", data);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }

        view.addObject("title", "Create New Variant");

        return view; 
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@ModelAttribute VariantView variant){
        ResponseEntity<VariantView> apiResponse = null;
        try {
            apiResponse = restTemplate.postForEntity(apiUrl + "/variant", variant , VariantView.class);
            if (apiResponse.getStatusCode() == HttpStatus.CREATED) {
                return new ResponseEntity<VariantView>(apiResponse.getBody(), HttpStatus.OK);
            } else {
                throw new Exception(apiResponse.getBody().toString() + ": " + apiResponse.getBody().toString());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    ModelAndView detail(@PathVariable int id) {
        ModelAndView view = new ModelAndView("/variant/detail");
        ResponseEntity<VariantView> apiResponse = null;
        ResponseEntity<List<CategoryView>> categoryResponse = null;

        try {
            // Mengambil data variant berdasarkan ID
            apiResponse = restTemplate.getForEntity(apiUrl + "/variant/id/" + id, VariantView.class);

            // Mengambil semua kategori
            categoryResponse = restTemplate.exchange(apiUrl + "/category", HttpMethod.GET, null, new ParameterizedTypeReference<List<CategoryView>>() {});

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                VariantView data = apiResponse.getBody();
                view.addObject("variant", data);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }

            if (categoryResponse.getStatusCode() == HttpStatus.OK) {
                List<CategoryView> categories = categoryResponse.getBody();
                view.addObject("category", categories);  // Menambahkan kategori ke model view
            } else {
                throw new Exception(categoryResponse.getStatusCode().toString() + ": " + categoryResponse.getBody());
            }


        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        
        view.addObject("title", "Detail Variant");
        
        return view;
    }


    @GetMapping("/edit/{id}")
    ModelAndView edit(@PathVariable int id) {
        ModelAndView view = new ModelAndView("/variant/edit");
        ResponseEntity<VariantView> apiResponse = null;
        ResponseEntity<List<CategoryView>> categoryResponse = null;

        try {
            // apiResponse = restTemplate.getForEntity(apiUrl + "/variant/id/" + id , VariantView.class);
            // if (apiResponse.getStatusCode() == HttpStatus.OK) {
            //     VariantView data = apiResponse.getBody();
            //     view.addObject("variant", data);
            // } else {
            //     throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            // }

            // Mengambil data variant berdasarkan ID
            apiResponse = restTemplate.getForEntity(apiUrl + "/variant/id/" + id, VariantView.class);

            // Mengambil semua kategori
            categoryResponse = restTemplate.exchange(apiUrl + "/category", HttpMethod.GET, null, new ParameterizedTypeReference<List<CategoryView>>() {});

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                VariantView data = apiResponse.getBody();
                view.addObject("variant", data);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }

            if (categoryResponse.getStatusCode() == HttpStatus.OK) {
                List<CategoryView> categories = categoryResponse.getBody();
                view.addObject("category", categories);  // Menambahkan kategori ke model view
            } else {
                throw new Exception(categoryResponse.getStatusCode().toString() + ": " + categoryResponse.getBody());
            }

        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        
        view.addObject("title", "Edit Variant");
        
        return view;
    }

    @PostMapping("/update")
    ResponseEntity<?> update(@ModelAttribute VariantView variant){
        ResponseEntity<VariantView> apiResponse = null;

        try {
            restTemplate.put(apiUrl + "/variant", variant);
            apiResponse = restTemplate.getForEntity(apiUrl + "/variant/id/" + variant.getId() , VariantView.class);

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<VariantView>(apiResponse.getBody(), HttpStatus.OK);
            } else {
                throw new Exception(apiResponse.getBody().toString() + ": " + apiResponse.getBody().toString());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable int id){
        ModelAndView view = new ModelAndView("/variant/delete");
        view.addObject("id", id);
        view.addObject("title", "Delete Confirmation");
        return view;
    }

    @PostMapping("/delete/{id}/{userId}")
    ModelAndView delete(@PathVariable int id, @PathVariable int userId) {
        ModelAndView view = new ModelAndView("/variant/delete");
        ResponseEntity<VariantView> apiResponse = null;
        VariantView variant = new VariantView();

        variant.setId(id);
        variant.setUpdateBy(userId);

        try {
            apiResponse = restTemplate.exchange(
                apiUrl + "/variant/delete/" + id + "/" + userId, 
                HttpMethod.DELETE, new HttpEntity<VariantView>(variant), 
                VariantView.class
            );
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                VariantView data = apiResponse.getBody();
                view.addObject("variant", data);
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

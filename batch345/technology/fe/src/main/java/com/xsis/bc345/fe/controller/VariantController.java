package com.xsis.bc345.fe.controller;

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

import com.xsis.bc345.fe.models.VariantView;
import com.xsis.bc345.fe.models.VariantView;

@Controller
@RequestMapping("/variant")
public class VariantController {

    // HTTP Client
    private RestTemplate restTemplate = new RestTemplate();

    // API Url
    private final String apiUrl = "http://localhost:8080/api/variant";

    @GetMapping("")
    public ModelAndView index(String filter) {
        ModelAndView view = new ModelAndView("/variant/index");

        ResponseEntity<VariantView[]> apiResponse = null;

        try {
            if (filter == null || filter.isBlank()) {

                apiResponse = restTemplate.getForEntity(apiUrl, VariantView[].class);
            } else {
                apiResponse = restTemplate.getForEntity(apiUrl + "/filter/" + filter, VariantView[].class);

            }
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                VariantView[] data = apiResponse.getBody();
                view.addObject("variant", data);
                // view.addObject("data", apiResponse.getBody()); ---ini juga bisa
            } else {
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

        ResponseEntity<VariantView> apiResponse = null;

        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "/add/", VariantView.class);

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                VariantView data = apiResponse.getBody();
                view.addObject("variant", data);
                // view.addObject("data", apiResponse.getBody()); ---ini juga bisa
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }

        } catch (Exception e) {
            // TODO: handle exception
            view.addObject("errorMsg", e.getMessage());
        }

        view.addObject("Variant Detail");

        return view;
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> getByCategory(@PathVariable int categoryId) {
        ResponseEntity<VariantView[]> apiResponse;

        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "/variant/category" + categoryId, VariantView[].class);
            return new ResponseEntity<VariantView[]>(apiResponse.getBody(), HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create")
    ResponseEntity<?> create(@ModelAttribute VariantView variant) {
        ResponseEntity<VariantView> apiResponse = null;

        try {
            apiResponse = restTemplate.postForEntity(apiUrl, variant, VariantView.class);

            if (apiResponse.getStatusCode() == HttpStatus.CREATED) {
                return new ResponseEntity<VariantView>(apiResponse.getBody(), HttpStatus.CREATED);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        ModelAndView view = new ModelAndView("/variant/edit");

        ResponseEntity<VariantView> apiResponse = null;

        ResponseEntity<VariantView[]> categoryResponse = null;


        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "/id/" + id, VariantView.class);
            
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                VariantView variant = apiResponse.getBody();
                view.addObject("title", "Edit variant");
                view.addObject("variant", variant);
            } else {
                throw new Exception("Error: " + apiResponse.getStatusCode());
            }

            // Fetch categories for dropdown
        categoryResponse = restTemplate.getForEntity("http://localhost:8080/api/category", VariantView[].class);
        if (categoryResponse.getStatusCode() == HttpStatus.OK) {
            VariantView[] categories = categoryResponse.getBody();
            view.addObject("categories", categories);
        } else {
            throw new Exception("Error: " + categoryResponse.getStatusCode());
        }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }

        return view;
    }

    @GetMapping("/{id}")
    public ModelAndView detail(@PathVariable int id) {
        ModelAndView view = new ModelAndView("/variant/detail");

        ResponseEntity<VariantView> apiResponse = null;

        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "/id/" + id, VariantView.class);

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                VariantView data = apiResponse.getBody();
                view.addObject("variant", data);
                // view.addObject("data", apiResponse.getBody()); ---ini juga bisa
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }

        } catch (Exception e) {
            // TODO: handle exception
            view.addObject("errorMsg", e.getMessage());
        }

        view.addObject("variant Detail");

        return view;
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@ModelAttribute VariantView variant) throws Exception {
        // TODO: process POST request
        ResponseEntity<VariantView> apiResponse = null;

        try {
            restTemplate.put(apiUrl, variant);
            apiResponse = restTemplate.getForEntity(apiUrl + "/id/" + variant.getId(), VariantView.class);

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<VariantView>(apiResponse.getBody(), HttpStatus.OK);
            } else {

                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/delete/{id}/{userId}")
    public ResponseEntity<?> delete(@PathVariable int id, @PathVariable int userId) {
        ResponseEntity<VariantView> apiResponse = null;
        VariantView variant = new VariantView();

        variant.setId(id);
        variant.setUpdateBy(userId);

        try {
            // restTemplate.delete(apiUrl + "/delete/" + id + "/" + userId);
            apiResponse = restTemplate.exchange(
                apiUrl + "/delete/" + id + "/" + userId,
                HttpMethod.DELETE,
                new HttpEntity<VariantView>(variant),
                VariantView.class
            );

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<VariantView>(apiResponse.getBody(), HttpStatus.OK);
            }
            else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " +  apiResponse.getBody().toString());
            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable int id){
        ModelAndView view = new ModelAndView("/variant/delete");

        view.addObject("id", id);
        view.addObject("title", "Delete Variant");

        return view;
    }

    
}
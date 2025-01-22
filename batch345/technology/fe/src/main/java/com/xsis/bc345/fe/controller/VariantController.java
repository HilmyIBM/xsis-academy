package com.xsis.bc345.fe.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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
    private String apiUrl;
    
    @GetMapping("")
    public ModelAndView index(String filter) {
        ModelAndView view = new ModelAndView("/variant/index");
        ResponseEntity<VariantView[]> apiResponse = null;

        try {
            apiResponse = restTemplate.getForEntity(
                apiUrl + "/variant" + (filter == null  || filter.isBlank() ? "" : "/filter/" + filter),
                VariantView[].class
            );
            
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

        view.addObject("filter", filter!=null ? filter : "");

        return view;
    }
 
    @GetMapping("/id/{id}")
    public ModelAndView detail(@PathVariable int id) {
        ModelAndView view = new ModelAndView("/variant/detail");
        ResponseEntity<VariantView> apiResponse = null;

        try {
            //Get the selected Variant
            apiResponse = restTemplate.getForEntity(apiUrl + "/variant/id/" + id, VariantView.class);
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

        view.addObject("title", "Variant Details");

        return view;
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> getByCategory(@PathVariable int categoryId) {
        ResponseEntity<VariantView[]> apiResponse;
        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "/variant/category/" + categoryId, VariantView[].class);

            return new ResponseEntity<VariantView[]>(apiResponse.getBody(), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
    public ResponseEntity<?> create(VariantView variant) {
        try {
            final ResponseEntity<VariantView> apiResponse = restTemplate.postForEntity(apiUrl + "/variant", variant, VariantView.class);

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

    @PostMapping("/update")
    public ResponseEntity<?> update(VariantView variant) {
        ResponseEntity<VariantView> apiResponse = null;

        try {
            apiResponse = restTemplate.exchange(apiUrl + "/variant", HttpMethod.PUT, new HttpEntity<VariantView>(variant), VariantView.class);

            if(apiResponse.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<VariantView>(apiResponse.getBody(), HttpStatus.OK);
            }
            else{
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        ModelAndView view = new ModelAndView("/variant/delete");

        view.addObject("id", id);
        view.addObject("title", "Delete Variant");

        return view;
    }

    @PostMapping("/delete/{id}/{userId}")
    public ResponseEntity<?> delete(@PathVariable int id, @PathVariable int userId) {
        ResponseEntity<VariantView> apiResponse = null;
        
        VariantView variant = new VariantView();
        variant.setId(id);
        variant.setUpdateBy(userId);

        try {
            apiResponse = restTemplate.exchange(
                apiUrl + "/variant/delete/" + id + "/" + userId,
                HttpMethod.DELETE,
                new HttpEntity<VariantView>(variant),
                VariantView.class
            );

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<VariantView>(apiResponse.getBody(),HttpStatus.OK);
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

package com.xsis.b345.frontend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.b345.frontend.models.categoryView;
import com.xsis.b345.frontend.models.variantView;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class variant {
    @Value("${application.api.url}")
    private String apiUrl;
    private RestTemplate restTemplate = new RestTemplate();
    // private final String apiUrl="http://localhost:8080/api/variant";
    private final String apiCategory = "http://localhost:8080/api/category";

    @GetMapping("/variant")
    public ModelAndView index( String filter) {
        ModelAndView view = new ModelAndView("/variant/index");
        ResponseEntity<variantView[]> apiResponse = null;
        try {
            if (filter == null || filter.isEmpty()) {
                apiResponse = restTemplate.getForEntity(apiUrl + "/variant", variantView[].class);
            } else {
                apiResponse = restTemplate.getForEntity(apiUrl + "/variant/filter/" + filter, variantView[].class);
            }
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                view.addObject("variant", apiResponse.getBody());
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + " : " + apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMSG", e.getMessage());
        }
        return view;
    }

    @GetMapping("/variant/add")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("variant/add");
        view.addObject("title", "Add new Variant");
        ResponseEntity<categoryView[]> apiResponseCategory = null;
        try {
            apiResponseCategory = restTemplate.getForEntity(apiCategory, categoryView[].class);
            if (apiResponseCategory.getStatusCode() == HttpStatus.OK) {
                view.addObject("category", apiResponseCategory.getBody());
            } else {
                view.addObject("category", new categoryView());
            }
        } catch (Exception e) {
            view.addObject("errorMSG", e.getMessage());
        }
        return view;
    }

    @PostMapping("/variant/save")
    ResponseEntity<?> save(@ModelAttribute variantView variant) {
        ResponseEntity<variantView> apiResponse = null;
        try {
            apiResponse = restTemplate.postForEntity(apiUrl + "/variant", variant, variantView.class);
            if (apiResponse.getStatusCode() == HttpStatus.CREATED) {
                return new ResponseEntity<variantView>(apiResponse.getBody(), HttpStatus.OK);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + " : " + apiResponse.getBody().toString());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("variant/edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        ModelAndView view = new ModelAndView("variant/edit");
        ResponseEntity<variantView[]> apiResponse = null;
        ResponseEntity<categoryView[]> apiResponseCategory = null;
        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "/variant/id/" + id, variantView[].class);
            apiResponseCategory = restTemplate.getForEntity(apiCategory, categoryView[].class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                view.addObject("variant", apiResponse.getBody());
                view.addObject("category", apiResponseCategory.getBody());
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + " : " + apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMSG", e.getMessage());
        }
        view.addObject("title", "Edit Variant");
        return view;
    }

    @PostMapping("/variant/update")
    public ResponseEntity<?> update(@ModelAttribute variantView variant) {
        ResponseEntity<variantView[]> apiResponse = null;
        try {
            restTemplate.put(apiUrl + "/variant", variant);
            apiResponse = restTemplate.getForEntity(apiUrl + "/variant/id/" + variant.getId(), variantView[].class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<variantView[]>(apiResponse.getBody(), HttpStatus.OK);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + " : " + apiResponse.getBody().toString());
            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("variant/detail/{id}")
    public ModelAndView detail(@PathVariable int id) {
        ModelAndView view = new ModelAndView("variant/detail");
        ResponseEntity<variantView[]> apiResponse = null;
        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "variant/id/" + id, variantView[].class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                view.addObject("variant", apiResponse.getBody());
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + " : " + apiResponse.getBody());
            }
        } catch (Exception e) {
            // TODO: handle exception
            view.addObject("errorMSG", e.getMessage());
        }
        view.addObject("title", "Detail Variant");
        return view;
    }

    @GetMapping("/variant/delete/{id}")
    public ModelAndView deleteView(@PathVariable int id) {
        ModelAndView view = new ModelAndView("variant/delete");
        view.addObject("id", id);
        view.addObject("title", "Hapus Variant");
        return view;
    }

    @PostMapping("/variant/delete/{id}/{userId}")
    public ResponseEntity<?> deleteData(@PathVariable int id, @PathVariable int userId) {
        ResponseEntity<variantView> apiResponse = null;
        try {
            apiResponse = restTemplate.exchange(apiUrl + "/variant/delete/" + id + "/" + userId, HttpMethod.DELETE,
                    null,
                    variantView.class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<variantView>(apiResponse.getBody(), HttpStatus.OK);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + " : " + apiResponse.getBody().toString());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("variant/category/{categoryId}")
    public ResponseEntity<?> getbyCategory(@PathVariable int categoryId){
        ResponseEntity<variantView[]> apiresponse;
        try {
            apiresponse=restTemplate.getForEntity(apiUrl+"/variant/category/"+categoryId, variantView[].class);
            if(apiresponse.getStatusCode()==HttpStatus.OK){
                return new ResponseEntity<variantView[]>(apiresponse.getBody(), HttpStatus.OK);
            }else{
                throw new Exception(apiresponse.getStatusCode().toString()+" : "+apiresponse.getBody().toString());
            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

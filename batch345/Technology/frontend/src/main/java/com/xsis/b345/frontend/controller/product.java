package com.xsis.b345.frontend.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.b345.frontend.models.categoryView;
import com.xsis.b345.frontend.models.productView;
import com.xsis.b345.frontend.models.variantView;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class product {
    @Value("${application.api.url}")
    private String apiUrl;
    private RestTemplate restTemplate = new RestTemplate();
    //private final String apiUrl="http://localhost:8080/api/product";
    private final String apiVariant="http://localhost:8080/api/variant";
    private final String apiCategory="http://localhost:8080/api/category";

    @GetMapping("/product")
    public ModelAndView product(){
        ModelAndView view = new ModelAndView("/product/index");
        ResponseEntity<productView[]> apiResponse=null;
        ResponseEntity<variantView[]> apiResponseVariant=null;
        ResponseEntity<categoryView[]> apiResponseCategory=null;
        try {
            apiResponse = restTemplate.getForEntity(apiUrl+"/product",productView[].class);
            apiResponseVariant=restTemplate.getForEntity(apiVariant, variantView[].class);
            apiResponseCategory=restTemplate.getForEntity(apiCategory, categoryView[].class);
            if (apiResponse.getStatusCode()==HttpStatus.OK) {
                view.addObject("product", apiResponse.getBody());
                view.addObject("variant",apiResponseVariant.getBody());
                view.addObject("category", apiResponseCategory.getBody());
            } else {
                throw new Exception(apiResponse.getStatusCode().toString()+" : "+apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMSG", e.getMessage());
        }
        return view;
    }

    @GetMapping("/product/add")
    public ModelAndView addData() {
        ModelAndView view= new ModelAndView("/product/add");
        view.addObject("title", "Add new product");
        ResponseEntity<variantView[]> apiResponsevariant=null;
        try {
            apiResponsevariant = restTemplate.getForEntity(apiVariant,variantView[].class);
            if (apiResponsevariant.getStatusCode()==HttpStatus.OK) {
                view.addObject("variant", apiResponsevariant.getBody());
            } else {
                view.addObject("variant", new variantView());
            }
        } catch (Exception e) {
            view.addObject("errorMSG", e.getMessage());
        }

        return view;
    }

    @PostMapping("/product/save")
    public ResponseEntity<?> save(@ModelAttribute productView product) {
        ResponseEntity<productView> apiResponse=null;
        try {
            apiResponse=restTemplate.postForEntity(apiUrl+"/product", product, productView.class);
            if (apiResponse.getStatusCode()==HttpStatus.CREATED) {
                return new ResponseEntity<productView>(apiResponse.getBody(),HttpStatus.OK);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString()+" : "+apiResponse.getBody().toString()); 
            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("product/edit/{id}")
    public ModelAndView edit(@PathVariable int id){
        ModelAndView view = new ModelAndView("product/edit");
        ResponseEntity<productView> apiResponse=null;
        ResponseEntity<variantView[]> apiResponseVariant=null;
        try{
            apiResponse=restTemplate.getForEntity(apiUrl+"/product/id/"+id, productView.class);
            apiResponseVariant=restTemplate.getForEntity(apiUrl+"/variant", variantView[].class);
            if (apiResponse.getStatusCode()==HttpStatus.OK) {
                view.addObject("product",apiResponse.getBody());  
                view.addObject("variant", apiResponseVariant.getBody());
            }else{
                throw new Exception(apiResponse.getStatusCode().toString()+" : "+apiResponse.getBody());
            }
        }catch(Exception e){
            view.addObject("errorMSG", e.getMessage());
        }
        view.addObject("title", "Edit Product");
        return view;
    }
}

package com.xsis.bc345.fe.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import com.xsis.bc345.fe.models.ProductView;
import com.xsis.bc345.fe.models.VariantView;


@Controller
@RequestMapping("/product")
public class ProductController {
    private RestTemplate restTemplate = new RestTemplate();
    //api url
    // private  final String apiUrl = "http://localhost:8080/api/category";
    @Value("${application.api.url}")
    private String apiUrl;
    
    @GetMapping("")
    public ModelAndView index(String filter) {
        ModelAndView view = new ModelAndView("product/index");
        ResponseEntity<ProductView[]> apiResponse = null;
        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "/product", ProductView[].class);
            // if(filter == null || filter.isBlank()){
                
            // }else{
            //     apiResponse = restTemplate.getForEntity(apiUrl+ "/category/filter/" + filter, CategoryView[].class);
            // }
            if(apiResponse.getStatusCode() == HttpStatus.OK){
                ProductView[] data = apiResponse.getBody();
                view.addObject("product", data);
            }else{
                throw new Exception(apiResponse.getStatusCode().toString()+ ": "+ apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        view.addObject("filter", filter);
        return view;
    }
    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("/product/add");
        ResponseEntity<VariantView[]> resVariant = null;
        try {
            resVariant = restTemplate.getForEntity(apiUrl + "/variant", VariantView[].class);
            if(resVariant.getStatusCode() == HttpStatus.OK){
                VariantView[] data = resVariant.getBody();
                view.addObject("variant", data);
            }else{
                throw new Exception(resVariant.getStatusCode().toString() + ": " + resVariant.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
            // TODO: handle exception
        }
        view.addObject("title", "Add Product");
        return view;
    }
    
}

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.bc345.fe.models.CategoryView;
import com.xsis.bc345.fe.models.VariantView;

@Controller
@RequestMapping("/variant")
public class VariantController {
    private RestTemplate restTemplate = new RestTemplate();

    //api url
    // private  final String apiUrl = "http://localhost:8080/api/variant";
    
    @Value("${application.api.url}")
    private String apiUrl;

    @GetMapping("")
    public ModelAndView index(String filter) {
        ModelAndView view = new ModelAndView("variant/index");
        ResponseEntity<VariantView[]> apiResponse;
        try {
            apiResponse = restTemplate.getForEntity(apiUrl +"/variant", VariantView[].class);

            System.out.println(apiResponse.getBody().toString());

            // if(apiResponse.getStatusCode() == HttpStatus.OK){
            //     VariantView[] data = apiResponse.getBody();
            //     view.addObject("variant", data);
            // }else{
            //     throw new Exception(apiResponse.getStatusCode().toString()+ ": "+ apiResponse.getBody());
            // }


            if(filter == null || filter.isBlank()){
                apiResponse = restTemplate.getForEntity(apiUrl + "/variant", VariantView[].class);
            }else{
                apiResponse = restTemplate.getForEntity(apiUrl+ "/variant/filter/" + filter, VariantView[].class);
            }
            if(apiResponse.getStatusCode() == HttpStatus.OK){
                VariantView[] data = apiResponse.getBody();
                view.addObject("variant", data);
            }else{
                throw new Exception(apiResponse.getStatusCode().toString()+ ": "+ apiResponse.getBody());
            }



        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        return view;
    }
    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("/variant/add");
        ResponseEntity<CategoryView[]> resCategory = null;
        try {
            resCategory = restTemplate.getForEntity(apiUrl +"/category", CategoryView[].class);
            if(resCategory.getStatusCode() == HttpStatus.OK){
                CategoryView[] data = resCategory.getBody();
                view.addObject("category", data);
            }else{
                throw new Exception(resCategory.getStatusCode().toString() + ": " + resCategory.getBody());
            }
        } catch (Exception e) {
           view.addObject("errorMsg", e.getMessage());
        }
        view.addObject("title", "Add New Variant");
        return view;
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@ModelAttribute VariantView variant) {
        ResponseEntity<VariantView> apiResponse = null;
        try {
            apiResponse = restTemplate.postForEntity(apiUrl + "/variant", variant,VariantView.class);
            if(apiResponse.getStatusCode() == HttpStatus.CREATED){
                return new ResponseEntity<VariantView>(apiResponse.getBody(), HttpStatus.OK);
            }else{
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/edit/{id}")
    ModelAndView edit(@PathVariable int id){
        ModelAndView view = new ModelAndView("/variant/edit");
        ResponseEntity<VariantView> apiResponse = null;
        try {
            apiResponse = restTemplate.getForEntity(apiUrl +"/variant/id/" + id, VariantView.class);
            if(apiResponse.getStatusCode() == HttpStatus.OK){
                view.addObject("title", "Edit Variant");
                view.addObject("variant", apiResponse.getBody());
            }else{
                throw new Exception(apiResponse.getStatusCode().toString() + " : " + apiResponse.getBody() + "  HALOOOOOOOOOOOOOOOOOOOOOO INI ERRORRRRR");
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        return view;
    }
    @PostMapping("/update")
    public ResponseEntity<?> update(@ModelAttribute VariantView variant) {
        ResponseEntity<VariantView> apiResponse = null;
        try {
            restTemplate.put(apiUrl+"/variant", variant);
            apiResponse = restTemplate.getForEntity(apiUrl +"/variant/id/" + variant.getId(), VariantView.class);
            if(variant.getName() == ""){

            }
            if(apiResponse.getStatusCode() == HttpStatus.OK){
                return new ResponseEntity<VariantView>(apiResponse.getBody(), HttpStatus.OK);
            }else{
                throw new Exception(apiResponse.getStatusCode().toString() + ": "+ apiResponse.getBody().toString());
            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/delete/{id}")
    ModelAndView delete(@PathVariable int id){
        ModelAndView view = new ModelAndView("/variant/delete");
        ResponseEntity<VariantView> apiResponse = null;
        try {
            apiResponse = restTemplate.getForEntity(apiUrl +"/variant/id/" + id, VariantView.class);
            if(apiResponse.getStatusCode() == HttpStatus.OK){
                view.addObject("title", "Delete Variant");
                view.addObject("variant", apiResponse.getBody());
            }else{
                throw new Exception(apiResponse.getStatusCode().toString() + " : " + apiResponse.getBody() + "  HALOOOOOOOOOOOOOOOOOOOOOO INI ERRORRRRR");
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        return view;
    }

    @PostMapping("/delete-confirm")
    public ResponseEntity<?> deleteConfirm(@ModelAttribute VariantView variant) {
        ResponseEntity<VariantView> apiResponse = null;
        System.out.println("haloo masuky");
        try {
            System.out.println("try");
            // restTemplate.delete(apiUrl+ "/delete/" + category.getId() + "/3"); 
            apiResponse = restTemplate.exchange(apiUrl+ "/variant/delete/" + variant.getId() + "/3", HttpMethod.DELETE, new HttpEntity<VariantView>(variant), VariantView.class);
            if(apiResponse.getStatusCode() == HttpStatus.OK){
                System.out.println("if");
                return new ResponseEntity<VariantView>(apiResponse.getBody(), HttpStatus.OK);
            }else{
                System.out.println("else");
                throw new Exception(apiResponse.getStatusCode().toString() + ": "+ apiResponse.getBody().toString());
            }
        } catch (Exception e) {
            System.out.println("catch");
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

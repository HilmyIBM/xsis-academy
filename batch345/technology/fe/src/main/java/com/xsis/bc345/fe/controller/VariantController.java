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
@RequestMapping("/variant")
public class VariantController {
    private RestTemplate restTemplate = new RestTemplate();

    //api url
    private  final String apiUrl = "http://localhost:8080/api/variant";
    @GetMapping("")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("variant/index");
        ResponseEntity<VariantView[]> apiResponse = null;
        try {
            apiResponse = restTemplate.getForEntity(apiUrl, VariantView[].class);
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
        view.addObject("title", "Add New Variant");
        return view;
    }
}

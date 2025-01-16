package com.xsis.master.category;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.ParameterizedType;
import java.time.LocalDateTime;
import java.util.*;

@Controller()
@RequestMapping("/category")
public class CategoryController {

    private static final Logger log = LoggerFactory.getLogger(CategoryController.class);
    private final RestTemplate restTemplate;

    private final Environment env;
    private final String API_URL;

    @Autowired
    public CategoryController(Environment env) {
        this.env = env;
        this.API_URL = env.getProperty("api.url") + "/category";
        this.restTemplate = new RestTemplate();
    }

    @GetMapping("")
    public ModelAndView category() {
        var view = new ModelAndView("master/category/index");
        ResponseEntity<List<CategoryModel>> apiResponse;

        try {
            apiResponse = restTemplate.exchange(API_URL, HttpMethod.GET,
                    new HttpEntity<>(new ArrayList<>()),
                    new ParameterizedTypeReference<>(){});

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                view.addObject("category", apiResponse.getBody());
                log.info("Request of API: {} Got {} Total of Data", API_URL, Objects.requireNonNull(apiResponse.getBody()).size());
                log.info("Request Status Code: {}", apiResponse.getStatusCode());

                return view;
            }

            view.addObject("error", apiResponse.getBody());
            log.error("Error Request of API: {} With Http Code {}",
                    API_URL,
                    apiResponse.getBody());

        } catch (Exception e) {
            view.addObject("error", e.getMessage());
            log.error("Error Request of API: {} Got Message {}", API_URL, e.getMessage());
        }

        return view;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        var view = new ModelAndView("master/category/add");

        view.addObject("title", "Add new category");

        return view;
    }

    @PostMapping("/add")
    public String saveCategory(@ModelAttribute CategoryModel model) {

//        if (model.getName().equalsIgnoreCase("") || model.getDescription().equalsIgnoreCase(""))
//            return "gagal";

        var currentDate = LocalDateTime.now();

        model.setCreateDate(currentDate);

        System.out.println(model.toString());

        return "sukses";
    }

    @GetMapping("/edit/{id}")
    ModelAndView edit(@PathVariable int id) {
        ModelAndView view = new ModelAndView("master/category/edit");


        view.addObject("title", "Edit Category");
        view.addObject("categoryaasdasd", "");

        return view;
    }

}

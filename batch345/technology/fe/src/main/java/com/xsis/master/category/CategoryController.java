package com.xsis.master.category;

import com.xsis.master.util.ErrorModel;
import com.xsis.master.util.ProcessAPI;
import com.xsis.master.util.RequestType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller()
@RequestMapping("/category")
public class CategoryController {

    private static final Logger log = LoggerFactory.getLogger(CategoryController.class);
    private final RestTemplate restTemplate;
    private final ProcessAPI<CategoryModel, CategoryModel> request;

    private final String API_URL;

    @Autowired
    public CategoryController(ProcessAPI<CategoryModel, CategoryModel> request, Environment env) {
        this.request = request;
        this.restTemplate = new RestTemplate();
        this.API_URL = env.getProperty("api.url") + "/category";
    }

    @GetMapping("/{type}/{id}")
    public ModelAndView detail(@PathVariable("type") RequestType type, @PathVariable("id") int id) {
        ModelAndView view = getViewModel(type);

        ResponseEntity<CategoryModel> apiResponse;

        var header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        var httpEntity = new HttpEntity<>(header);

        try {
            apiResponse = restTemplate.exchange(API_URL + "/" + id,
                    HttpMethod.GET, httpEntity, CategoryModel.class);

            if (apiResponse.getStatusCode() == HttpStatus.OK)
                view.addObject("category", apiResponse.getBody());

        } catch (HttpClientErrorException e) {
            ErrorModel er = e.getResponseBodyAs(ErrorModel.class);

            log.error(Objects.requireNonNull(er).toString());
            view.addObject("error", er);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }

        return view;
    }

    @GetMapping("/add")
    ModelAndView add() {
        ModelAndView view = new ModelAndView("master/category/add");
        view.addObject("title", "Add New Category");

        return view;
    }

    @GetMapping("")
    public ModelAndView categories() {
        var view = new ModelAndView("master/category/index");
        ResponseEntity<List<CategoryModel>> apiResponse;

        try {
            apiResponse = restTemplate.exchange(API_URL, HttpMethod.GET,
                    new HttpEntity<>(new ArrayList<>()),
                    new ParameterizedTypeReference<>(){});

            if (apiResponse.getStatusCode() == HttpStatus.OK || apiResponse.getStatusCode() == HttpStatus.NO_CONTENT) {
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

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@ModelAttribute CategoryModel model) {
        return request.send(model, CategoryModel.class,
                HttpMethod.POST, HttpStatus.CREATED,
                API_URL);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateCategory(@ModelAttribute CategoryModel model) {
        return request.send(model, CategoryModel.class,
                HttpMethod.PUT, HttpStatus.OK,
                API_URL);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteCategory(@ModelAttribute CategoryModel model) {
        return request.send(model, CategoryModel.class,
                HttpMethod.DELETE, HttpStatus.OK,
                API_URL);
    }


    private ModelAndView getViewModel(RequestType type) {
        Map<String, Object> attributes = new HashMap<>();

        switch (type) {
            case DETAIL -> {
                attributes.put("title", "Category Details");
                return new ModelAndView("master/category/detail", attributes); }
            case EDIT -> {
                attributes.put("title", "Edit Category");
                return new ModelAndView("master/category/edit", attributes); }
            case DELETE -> {
                attributes.put("title", "Delete Category");
                return new ModelAndView("master/category/delete", attributes); }
            default -> throw new UnsupportedOperationException();
        }
    }
}

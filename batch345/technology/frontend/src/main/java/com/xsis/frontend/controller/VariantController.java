package com.xsis.frontend.controller;

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

import com.xsis.frontend.model.CategoryView;
import com.xsis.frontend.model.VariantView;

@Controller
@RequestMapping("/variant")
public class VariantController {
    private RestTemplate restTemplate = new RestTemplate();

    private final String apiUrl = "http://localhost:8080/api";

    @GetMapping("")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("variant/index");
        ResponseEntity<VariantView[]> variantResponse = null;
        try {
            variantResponse = restTemplate.getForEntity(apiUrl + "/variants", VariantView[].class);

            if (variantResponse.getStatusCode() == HttpStatus.OK) {
                VariantView[] variants = variantResponse.getBody();

                view.addObject("variant", variants);
            } else {
                throw new Exception(variantResponse.getStatusCode().toString() + ": " + variantResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }

        return view;
    }

    @SuppressWarnings("null")
    @GetMapping("/{id}")
    public ModelAndView detail(@PathVariable int id) {
        ModelAndView view = new ModelAndView("variant/detail");
        ResponseEntity<VariantView> resVariant = null;
        ResponseEntity<CategoryView> resCategory = null;

        try {
            resVariant = restTemplate.getForEntity(apiUrl + "/variants/id/" + id, VariantView.class);
            resCategory = restTemplate.getForEntity(apiUrl + "/categories/id/" + resVariant.getBody().getCategoryId(),
                    CategoryView.class);

            if (resVariant.getStatusCode() == HttpStatus.OK || resCategory.getStatusCode() == HttpStatus.OK) {
                VariantView data = resVariant.getBody();
                data.setCategoryName(resCategory.getBody().getCategoryName());
                view.addObject("variant", data);
            } else {
                throw new Exception(resVariant.getStatusCode().toString() + ": " + resVariant.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        view.addObject("title", "Variant Detail");

        return view;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("variant/add");
        ResponseEntity<CategoryView[]> resCategory = null;

        try {
            resCategory = restTemplate.getForEntity(apiUrl + "/categories", CategoryView[].class);
            if (resCategory.getStatusCode() == HttpStatus.OK) {
                CategoryView[] data = resCategory.getBody();
                view.addObject("category", data);
            } else {
                throw new Exception(resCategory.getStatusCode().toString() + ": " + resCategory.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        view.addObject("title", "Add New Category");

        return view;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@ModelAttribute VariantView variant) {
        ResponseEntity<VariantView> apiResponse = null;
        try {
            apiResponse = restTemplate.postForEntity(apiUrl + "/variants", variant, VariantView.class);

            if (apiResponse.getStatusCode() == HttpStatus.CREATED) {
                return new ResponseEntity<VariantView>(apiResponse.getBody(), HttpStatus.OK);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @SuppressWarnings("null")
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        ModelAndView view = new ModelAndView("variant/edit");
        ResponseEntity<VariantView> variantResponse = null;
        ResponseEntity<CategoryView[]> categoriesResponse = null;
        VariantView variant = new VariantView();

        try {
            variantResponse = restTemplate.getForEntity(apiUrl + "/variants/id/" + id,
                    VariantView.class);
            categoriesResponse = restTemplate.getForEntity(apiUrl + "/categories",
                    CategoryView[].class);
            if (variantResponse.getStatusCode() == HttpStatus.OK
                    && categoriesResponse.getStatusCode() == HttpStatus.OK) {
                variant = variantResponse.getBody();
                CategoryView[] categories = categoriesResponse.getBody();
                view.addObject("variant", variant);
                view.addObject("categories", categories);
            } else {
                throw new Exception(variantResponse.getStatusCode().toString() + ": " + variantResponse.getBody() + "\n"
                        + categoriesResponse.getStatusCode().toString() + ": " + categoriesResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        view.addObject("title", "Edit Variant");
        return view;
    }

    @SuppressWarnings("null")
    @PostMapping("/update")
    public ResponseEntity<?> update(@ModelAttribute VariantView variant) {
        ResponseEntity<VariantView> response = null;
        try {
            response = restTemplate.exchange(apiUrl + "/variants/id/" + variant.getId(), HttpMethod.PUT,
                    new HttpEntity<VariantView>(variant), VariantView.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<VariantView>(response.getBody(), HttpStatus.OK);
            } else {
                throw new Exception(response.getStatusCode().toString() + ": " + response.getBody().toString());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

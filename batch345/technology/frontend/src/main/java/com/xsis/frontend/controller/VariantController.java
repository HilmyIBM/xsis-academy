package com.xsis.frontend.controller;

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

import com.xsis.frontend.model.CategoryView;
import com.xsis.frontend.model.VariantView;

@Controller
@RequestMapping("/variant")
public class VariantController {
    private RestTemplate restTemplate = new RestTemplate();

    @Value("${application.api.url}")
    private String apiUrl;

    // private final String apiUrl = "http://localhost:8080/api";

    @GetMapping("")
    public ModelAndView index(String filter) {
        ModelAndView view = new ModelAndView("variant/index");
        ResponseEntity<VariantView[]> resVariant = null;
        try {
            if (filter == null || filter.isBlank()) {
                resVariant = restTemplate.getForEntity(apiUrl + "/variants", VariantView[].class);
            } else {
                resVariant = restTemplate.getForEntity(apiUrl + "/variants/filter/" + filter, VariantView[].class);
            }

            if (resVariant.getStatusCode() == HttpStatus.OK) {
                VariantView[] data = resVariant.getBody();
                view.addObject("variant", data);
            } else {
                throw new Exception(resVariant.getStatusCode().toString() + ": " + resVariant.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        view.addObject("filter", filter);
        return view;
    }

   
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

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        ModelAndView view = new ModelAndView("variant/edit");
        ResponseEntity<VariantView> resVariant = null;
        ResponseEntity<CategoryView[]> resCategories = null;
        VariantView variant = new VariantView();

        try {
            resVariant = restTemplate.getForEntity(apiUrl + "/variants/id/" + id,
                    VariantView.class);
            resCategories = restTemplate.getForEntity(apiUrl + "/categories",
                    CategoryView[].class);
            if (resVariant.getStatusCode() == HttpStatus.OK
                    && resCategories.getStatusCode() == HttpStatus.OK) {
                variant = resVariant.getBody();
                CategoryView[] categories = resCategories.getBody();
                view.addObject("variant", variant);
                view.addObject("categories", categories);
            } else {
                throw new Exception(resVariant.getStatusCode().toString() + ": " + resVariant.getBody() + "\n"
                        + resCategories.getStatusCode().toString() + ": " + resCategories.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        view.addObject("title", "Edit Variant");
        return view;
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@ModelAttribute VariantView variant) {
        ResponseEntity<VariantView> response = null;
        try {
            restTemplate.put(apiUrl + "/variants", variant);
            response = restTemplate.getForEntity(apiUrl + "/variants/id/" + variant.getId(), VariantView.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<VariantView>(response.getBody(), HttpStatus.OK);
            } else {
                throw new Exception(response.getStatusCode().toString() + ": " + response.getBody().toString());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        ModelAndView view = new ModelAndView("variant/delete");

        view.addObject("id", id);
        view.addObject("title", "Delete Variant");
        return view;
    }

   
    @PostMapping("/delete/{id}/{userId}")
    public ResponseEntity<?> deleteVariant(@PathVariable int id, @PathVariable int userId) {
        ResponseEntity<VariantView> response = null;
        VariantView variant = new VariantView();

        variant.setId(id);
        variant.setUpdateBy(userId);
        try {
            restTemplate.delete(apiUrl + "/variants/delete/" + id + "/" + userId);
            response = restTemplate.exchange(apiUrl + "/variants/delete/" + id + "/" + userId, HttpMethod.DELETE,
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

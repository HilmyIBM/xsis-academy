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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.bc345.fe.models.CategoryView;
import com.xsis.bc345.fe.models.VariantView;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("variant")
public class VariantController {

    private RestTemplate restTemplate = new RestTemplate();

    // private final String apiUrl = "http://localhost:8080/api/";
    @Value("${application.api.url}")
    private String apiUrl;

    @GetMapping("")
    public ModelAndView categoryVariant(String variantFilter) {
        ModelAndView view = new ModelAndView("variant/index");
        ResponseEntity<VariantView[]> apiResponse = null;
        try {
            if (variantFilter == null || variantFilter.isBlank()) {
                apiResponse = restTemplate.exchange(apiUrl + "variant", HttpMethod.GET, null, VariantView[].class);
            } else {
                apiResponse = restTemplate.exchange(apiUrl + "variant/filter/" + variantFilter, HttpMethod.GET, null,
                        VariantView[].class);
            }
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                view.addObject("variants", apiResponse.getBody());
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
            System.out.println("Error: " + e.getMessage());
        }
        return view;
    }

    @GetMapping("{id}")
    public ModelAndView getDetailVariant(@PathVariable int id) {
        ModelAndView view = new ModelAndView("variant/detail");
        ResponseEntity<VariantView> apiResponse = null;
        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "variant/id/" + id, VariantView.class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                view.addObject("variant", apiResponse.getBody());
            } else {
                throw new Exception("Error when fetching variant data");
            }
        } catch (Exception e) {
            view.addObject("errMsg", e.getLocalizedMessage());
        }
        view.addObject("title", "Detail Variant");
        return view;
    }

    @GetMapping("add")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("variant/add");
        // Get list of Category
        ResponseEntity<CategoryView[]> apiResponse = null;
        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "/category", CategoryView[].class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                view.addObject("category", apiResponse.getBody());
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            view.addObject("errorMsg", e.getMessage());
        }
        view.addObject("title", "Add New Variant");
        view.addObject("nama", "Menambahkan Variant");
        return view;
    }

    // Update the edit
    @PostMapping("edit")
    public ResponseEntity<?> update(@ModelAttribute VariantView variant) {
        ResponseEntity<VariantView> apiResponse = null;
        try {
            apiResponse = restTemplate.exchange(apiUrl + "variant", HttpMethod.PUT,
                    new HttpEntity<VariantView>(variant), VariantView.class);
            return new ResponseEntity<VariantView>(apiResponse.getBody(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Show the modal edit with data
    @GetMapping("edit/{id}")
    ModelAndView edit(@PathVariable int id) {
        ModelAndView view = new ModelAndView("variant/edit");
        ResponseEntity<VariantView> apiResponse = null;
        ResponseEntity<CategoryView[]> categoryResponse = null;

        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "variant/id/" + id, VariantView.class);

            // Mengambil semua kategori
            categoryResponse = restTemplate.exchange(apiUrl + "category", HttpMethod.GET, null, CategoryView[].class);

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                VariantView data = apiResponse.getBody();
                view.addObject("variant", data);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }

            if (categoryResponse.getStatusCode() == HttpStatus.OK) {
                view.addObject("category", categoryResponse.getBody());
            } else {
                throw new Exception(categoryResponse.getStatusCode().toString() + ": " + categoryResponse.getBody());
            }

        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        view.addObject("title", "Edit Variant");
        return view;
    }

    @GetMapping("delete/{id}")
    public ModelAndView deleteVariant(@PathVariable int id) {
        ModelAndView view = new ModelAndView("variant/delete");
        view.addObject("id", id);
        view.addObject("title", "Delete Variant");
        return view;
    }

    // Add
    @PostMapping("save")
    public ResponseEntity<?> save(@ModelAttribute VariantView variant) {
        ResponseEntity<VariantView> apiRepsonse = null;
        variant.setCreateBy(100);
        try {
            apiRepsonse = restTemplate.exchange(apiUrl + "/variant", HttpMethod.POST,
                    new HttpEntity<VariantView>(variant), VariantView.class);
            if (apiRepsonse.getStatusCode() == HttpStatus.CREATED) {
                return new ResponseEntity<VariantView>(apiRepsonse.getBody(), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<String>(apiRepsonse.getBody().toString(), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("delete/{id}/{userId}")
    public ResponseEntity<?> deleteVariant(@PathVariable int id, @PathVariable int userId) {
        ResponseEntity<VariantView> apiResponse = null;
        VariantView variant = new VariantView();
        variant.setId(id);
        variant.setUpdateBy(userId);
        try {
            apiResponse = restTemplate.exchange(apiUrl + "/variant/delete/" + id + "/" + userId, HttpMethod.DELETE,
                    new HttpEntity<VariantView>(variant), VariantView.class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<VariantView>(apiResponse.getBody(), HttpStatus.OK);
            }
            throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody().toString());
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

package com.xsis.b345.frontend.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
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
    private final String apiVariant = "http://localhost:8080/api/variant";

    @GetMapping("/product")
    public ModelAndView product(String filter) {
        ModelAndView view = new ModelAndView("/product/index");
        ResponseEntity<productView[]> apiResponse = null;
        try {
            if (filter==null || filter.isEmpty()) {
                apiResponse = restTemplate.getForEntity(apiUrl + "/product", productView[].class);
            } else {
                apiResponse = restTemplate.getForEntity(apiUrl + "/product/filter/" + filter, productView[].class);      
            } 
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                view.addObject("product", apiResponse.getBody());
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + " : " + apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMSG", e.getMessage());
        }
        return view;
    }

    @GetMapping("/product/add")
    public ModelAndView addData() {
        ModelAndView view = new ModelAndView("/product/add");
        view.addObject("title", "Add new product");
        ResponseEntity<variantView[]> apiResponsevariant = null;
        ResponseEntity<categoryView[]> apiResponseCategory = null;
        try {
            apiResponsevariant = restTemplate.getForEntity(apiVariant, variantView[].class);
            apiResponseCategory = restTemplate.getForEntity(apiUrl + "/category", categoryView[].class);
            if (apiResponsevariant.getStatusCode() == HttpStatus.OK) {
                view.addObject("variant", apiResponsevariant.getBody());
                view.addObject("category", apiResponseCategory.getBody());
            } else {
                view.addObject("variant", new variantView());
            }
        } catch (Exception e) {
            view.addObject("errorMSG", e.getMessage());
        }

        return view;
    }

    @PostMapping("/product/save")
    public ResponseEntity<?> save(@ModelAttribute productView product, @RequestParam("Setimage") MultipartFile file) {
        ResponseEntity<productView> apiResponse = null;
        try {
            if (!file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                Path path = Paths.get("src/main/resources/static/lib/images/" + fileName);
                Files.write(path, file.getBytes());
                product.setImage(fileName);
            }
            apiResponse = restTemplate.postForEntity(apiUrl + "/product", product, productView.class);
            if (apiResponse.getStatusCode() == HttpStatus.CREATED) {
                return new ResponseEntity<productView>(apiResponse.getBody(), HttpStatus.OK);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + " : " + apiResponse.getBody().toString());
            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("product/edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        ModelAndView view = new ModelAndView("product/edit");
        ResponseEntity<productView[]> apiResponse = null;
        ResponseEntity<variantView[]> apiResponseVariant = null;
        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "/product/id/" + id, productView[].class);
            apiResponseVariant = restTemplate.getForEntity(apiUrl + "/variant", variantView[].class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                view.addObject("product", apiResponse.getBody());
                view.addObject("variant", apiResponseVariant.getBody());
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + " : " + apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMSG", e.getMessage());
        }
        view.addObject("title", "Edit Product");
        return view;
    }

    @PostMapping("/product/update")
    public ResponseEntity<?> update(@ModelAttribute productView product, @RequestParam("Setimage") MultipartFile file) {
        ResponseEntity<productView[]> apiResponse = null;
        try {
            if (!file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                Path path = Paths.get("src/main/resources/static/lib/images/" + fileName);
                Files.write(path, file.getBytes());
                product.setImage(fileName);
            }
            restTemplate.put(apiUrl + "/product", product);
            apiResponse = restTemplate.getForEntity(apiUrl + "/product/id/" + product.getId(), productView[].class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<productView[]>(apiResponse.getBody(), HttpStatus.OK);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + " : " + apiResponse.getBody().toString());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("product/detail/{id}")
    public ModelAndView detail(@PathVariable int id) {
        ModelAndView view = new ModelAndView("product/detail");
        ResponseEntity<productView[]> apiResponse = null;
        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "product/id/" + id, productView[].class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                view.addObject("product", apiResponse.getBody());
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + " : " + apiResponse.getBody());
            }
        } catch (Exception e) {
            // TODO: handle exception
            view.addObject("errorMSG", e.getMessage());
        }

        view.addObject("title", "Detail Product");
        return view;
    }

    @GetMapping("/product/delete/{id}")
    public ModelAndView deleteView(@PathVariable int id) {
        ModelAndView view = new ModelAndView("product/delete");
        view.addObject("id", id);
        view.addObject("title", "Hapus Product");
        return view;
    }

    @PostMapping("/product/delete/{id}/{userId}")
    public ResponseEntity<?> deleteData(@PathVariable int id, @PathVariable int userId) {
        ResponseEntity<productView> apiResponse = null;
        try {
            apiResponse = restTemplate.exchange(apiUrl + "/product/delete/" + id + "/" + userId, HttpMethod.DELETE,
                    null,
                    productView.class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<productView>(apiResponse.getBody(), HttpStatus.OK);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + " : " + apiResponse.getBody().toString());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

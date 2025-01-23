package com.xsis.bc345.fe.controller;

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

import com.xsis.bc345.fe.models.ProductView;
import com.xsis.bc345.fe.models.VariantView;

@Controller
@RequestMapping("product")
public class ProductController {

    // @GetMapping("")
    // public ModelAndView index() {
    //     ModelAndView view = new ModelAndView("/product/index");

    //     return view;
    // }

    // HTTP Client
    private RestTemplate restTemplate = new RestTemplate();

    // API Url
    private final String apiUrl = "http://localhost:8080/api/product";

    @GetMapping("")
    public ModelAndView index(String filter) {
        ModelAndView view = new ModelAndView("/product/index");

        ResponseEntity<ProductView[]> apiResponse = null;

        try {
            if (filter == null || filter.isBlank()) {

                apiResponse = restTemplate.getForEntity(apiUrl, ProductView[].class);
            } else {
                apiResponse = restTemplate.getForEntity(apiUrl + "/filter/" + filter, ProductView[].class);

            }
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                ProductView[] data = apiResponse.getBody();
                view.addObject("product", data);
                // view.addObject("data", apiResponse.getBody()); ---ini juga bisa
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }

        } catch (Exception e) {
            // TODO: handle exception
            view.addObject("errorMsg", e.getMessage());
        }

        return view;
    }

     @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        ModelAndView view = new ModelAndView("/product/edit");

        ResponseEntity<ProductView> apiResponse = null;
        ResponseEntity<ProductView[]> variantApiResponse = null;

        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "/id/" + id, ProductView.class);

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                ProductView product = apiResponse.getBody();
                view.addObject("title", "Edit Product");
                view.addObject("product", product);
            } else {
                throw new Exception("Error: " + apiResponse.getStatusCode());
            }

              // Fetch Variants for dropdown
        variantApiResponse = restTemplate.getForEntity("http://localhost:8080/api/variant", ProductView[].class);
        if (variantApiResponse.getStatusCode() == HttpStatus.OK) {
            ProductView[] variant = variantApiResponse.getBody();
            view.addObject("variant", variant);
        } else {
            throw new Exception("Error: " + variantApiResponse.getStatusCode());
        }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        
        return view;
    }

    @GetMapping("/edit/old/{id}")
    public ModelAndView editOld(@PathVariable int id) {
        ModelAndView view = new ModelAndView("/product/edit");

        ResponseEntity<ProductView> apiResponse = null;

        ResponseEntity<ProductView[]> variantApiResponse = null;

        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "/id/" + id, ProductView.class);

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                ProductView product = apiResponse.getBody();
                view.addObject("title", "Edit Product");
                view.addObject("product", product);
            } else {
                throw new Exception("Error: " + apiResponse.getStatusCode());
            }

              // Fetch Variants for dropdown
        variantApiResponse = restTemplate.getForEntity("http://localhost:8080/api/variant", ProductView[].class);
        if (variantApiResponse.getStatusCode() == HttpStatus.OK) {
            ProductView[] variant = variantApiResponse.getBody();
            view.addObject("variant", variant);
        } else {
            throw new Exception("Error: " + variantApiResponse.getStatusCode());
        }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }

        return view;
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@ModelAttribute ProductView product) throws Exception {
        // TODO: process POST request
        ResponseEntity<ProductView> apiResponse = null;

        try {
            restTemplate.put(apiUrl, product);
            apiResponse = restTemplate.getForEntity(apiUrl + "/id/" + product.getId(), ProductView.class);

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<ProductView>(apiResponse.getBody(), HttpStatus.OK);
            } else {

                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{id}")
    public ModelAndView detail(@PathVariable int id) {
        ModelAndView view = new ModelAndView("/product/detail");

        ResponseEntity<ProductView> apiResponse = null;

        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "/id/" + id, ProductView.class);

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                ProductView data = apiResponse.getBody();
                view.addObject("product", data);
                // view.addObject("data", apiResponse.getBody()); ---ini juga bisa
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }

        } catch (Exception e) {
            // TODO: handle exception
            view.addObject("errorMsg", e.getMessage());
        }

        view.addObject("Product Detail");

        return view;
    }


}

package com.xsis.bc345.fe.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.bc345.fe.models.ProductView;
import com.xsis.bc345.fe.models.VariantView;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("product")
public class ProductController {
    private RestTemplate restTemplate = new RestTemplate();

    @Value("${application.api.url}")
    private String apiUrl;

    @GetMapping("")
    public ModelAndView categoryProduct() {
        ModelAndView view = new ModelAndView("product/index");
        ResponseEntity<ProductView[]> apiResponse = null;
        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "product", ProductView[].class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                view.addObject("products", apiResponse.getBody());
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        return view;
    }

    @GetMapping("add")
    public ModelAndView addProduct() {
        ModelAndView view = new ModelAndView("product/add");
        view.addObject("title", "Add New Product");

        ResponseEntity<VariantView[]> apiResponse = null;
        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "variant", VariantView[].class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                view.addObject("variants", apiResponse.getBody());
            } else {
                throw new Exception("Error fetching variants list");
            }
        } catch (Exception e) {
            view.addObject("errMsg", e.getLocalizedMessage());
        }
        return view;
    }

    @PostMapping("save")
    public ResponseEntity<?> save(@ModelAttribute ProductView product) {
        ResponseEntity<ProductView> apiResponse = null;
        product.setCreateBy(50);
        try {
            apiResponse = restTemplate.exchange(apiUrl + "product", HttpMethod.POST,
                    new HttpEntity<ProductView>(product), ProductView.class);
            if (apiResponse.getStatusCode() == HttpStatus.CREATED) {
                return new ResponseEntity<ProductView>(apiResponse.getBody(), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<String>(apiResponse.getBody().toString(), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage()   );
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

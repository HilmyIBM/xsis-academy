package com.xsis.bc345.fe.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
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
    public ResponseEntity<?> saveProduct(@ModelAttribute ProductView productView,
            @RequestParam("myImage") MultipartFile image) {
        ResponseEntity<ProductView> apiResponse = null;
        try {
            // Handle the file
            if (!image.isEmpty()) {
                // Get the name
                String imageName = image.getOriginalFilename();
                // get the path
                Path path = Paths.get("src/main/resources/static/lib/images/" + imageName);
                // get the directory
                Path directory = path.getParent();
                // Check if the directory is not exists
                if (!Files.exists(directory)) {
                    Files.createDirectories(directory);
                }
                // Save the file to the path
                Files.write(path, image.getBytes());
                productView.setImage(imageName);
            }

            apiResponse = restTemplate.postForEntity(apiUrl + "product", productView,
                    ProductView.class);
            if (apiResponse.getStatusCode() == HttpStatus.CREATED) {
                return new ResponseEntity<ProductView>(apiResponse.getBody(), HttpStatus.CREATED);
            } else {
                throw new Exception(apiResponse.getBody().toString() + ": " + HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        ModelAndView view = new ModelAndView("product/edit");
        ResponseEntity<ProductView> apiResponse = null;
        ResponseEntity<VariantView[]> apiResponseVariant = null;

        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "product/id/" + id, ProductView.class);
            apiResponseVariant = restTemplate.getForEntity(apiUrl + "variant", VariantView[].class);
            if (apiResponse.getStatusCode() == HttpStatus.OK && apiResponseVariant.getStatusCode() == HttpStatus.OK) {
                view.addObject("product", apiResponse.getBody());
                view.addObject("variants", apiResponseVariant.getBody());
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody().toString());
            }
        } catch (Exception e) {
            view.addObject("errMsg", e.getLocalizedMessage());
        }
        view.addObject("title", "Edit Product");
        return view;
    }
}

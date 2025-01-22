package com.xsis.bc345.fe.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.bc345.fe.models.CategoryView;
import com.xsis.bc345.fe.models.ProductView;

@Controller
public class ProductController {  
    //HTTP Client
    private RestTemplate restTemplate = new RestTemplate();
    
    //Image Folder Name
    @Value("${application.imageFolder}")
    private String IMG_FOLDER;
    
    //API URL
    @Value("${application.api.url}")
    private String apiUrl;

    @GetMapping("/product")
    public ModelAndView index(String filter) {
        ModelAndView view = new ModelAndView("/product/index");
        ResponseEntity<ProductView[]> apiResponse = null;

        try {
            if (filter == null || filter.isBlank()){
                apiResponse = restTemplate.getForEntity(apiUrl + "/product", ProductView[].class);
            }
            else {
                apiResponse = restTemplate.getForEntity(apiUrl + "/product/filter/" + filter, ProductView[].class);
            }

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                view.addObject("product", apiResponse.getBody());
            }
            else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }

        return view;
    }

    @GetMapping("/product/add")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("/product/add");
        //Category List
        ResponseEntity<CategoryView[]> apiCategoryResponse = null;

        try {
            apiCategoryResponse = restTemplate.getForEntity(apiUrl + "/category", CategoryView[].class);

            view.addObject(
                "categories", 
                (apiCategoryResponse.getStatusCode() == HttpStatus.OK) ? apiCategoryResponse.getBody() : new CategoryView()
            );

        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }

        view.addObject("title", "Add New Product");

        return view;
    }

    @PostMapping(path = "/product/create")
    public ResponseEntity<?> create(@ModelAttribute ProductView data, @RequestParam("imageFile") MultipartFile imageFile) throws Exception {
        //Upload Image File Process
        if(imageFile.getOriginalFilename() != null || imageFile.getOriginalFilename() != ""){
            byte[] _bytes = imageFile.getBytes();
            Path path = Paths.get(IMG_FOLDER+"/"+imageFile.getOriginalFilename());
            Files.write(path, _bytes);
        }

        ProductView newProduct = new ProductView();
        newProduct.setName(data.getName());
        newProduct.setPrice(data.getPrice());
        newProduct.setStock(data.getStock());
        newProduct.setVariantId(data.getVariantId());
        newProduct.setCreateBy(data.getCreateBy());
        newProduct.setImage(imageFile.getOriginalFilename());

        try {
            ResponseEntity<ProductView> apiResponse = restTemplate.postForEntity(apiUrl + "/product", newProduct, ProductView.class);
            
            if (apiResponse.getStatusCode() == HttpStatus.CREATED){
                return new ResponseEntity<ProductView>(apiResponse.getBody(), apiResponse.getStatusCode());    
            }
            else{
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

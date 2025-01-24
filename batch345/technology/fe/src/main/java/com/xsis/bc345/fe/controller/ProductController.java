package com.xsis.bc345.fe.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.bc345.fe.models.CategoryView;
import com.xsis.bc345.fe.models.ProductView;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/product")
public class ProductController {  
    //HTTP Client
    private RestTemplate restTemplate = new RestTemplate();
    
    //Image Folder Name
    @Value("${application.imageFolder}")
    private String IMG_FOLDER;
    
    //API URL
    @Value("${application.api.url}")
    private String apiUrl;

    @GetMapping("")
    public ModelAndView index(String filter, HttpSession sess) {
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

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable int id){
        ModelAndView view = new ModelAndView("/product/edit");

        view.addObject("id", id);
        view.addObject("title", "Edit Product");

        return view;
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody ProductView data, @RequestParam MultipartFile imageFile) {
        try {
            ResponseEntity<ProductView> apiResponse = new ResponseEntity<ProductView>(new ProductView(), HttpStatus.OK);

            return apiResponse;
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/add")
    public ModelAndView add(HttpSession sess) {
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
        view.addObject("cobaSession", sess.getAttribute("coba"));

        return view;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@ModelAttribute ProductView data, @RequestParam("imageFile") MultipartFile imageFile) throws Exception {
        //Upload Image File Process
        if(imageFile.getOriginalFilename() != null || imageFile.getOriginalFilename() != ""){
            String fileName = imageFile.getOriginalFilename();
            Path path = Paths.get(IMG_FOLDER+"/"+ fileName);
            Files.write(path, imageFile.getBytes());

            data.setImage(fileName);
        }

        try {
            ResponseEntity<ProductView> apiResponse = restTemplate.postForEntity(apiUrl + "/product", data, ProductView.class);
            
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

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        ModelAndView view = new ModelAndView("/product/delete");

        view.addObject("id", id);
        view.addObject("title", "Delete Product");

        return view;
    }

    @PostMapping("/delete/{id}/{userId}")
    public ResponseEntity<?> delete (@PathVariable int id, @PathVariable int userId) {
        ResponseEntity<ProductView> apiResponse;

        ProductView product = new ProductView();
        product.setId(id);
        product.setUpdateBy(userId);

        try {
            apiResponse = restTemplate.exchange(
                apiUrl + "/product/delete/" + id + "/" + userId,
                HttpMethod.DELETE,
                new HttpEntity<ProductView>(product),
                ProductView.class
            );
            
            if (apiResponse.getStatusCode() == HttpStatus.OK){
                return new ResponseEntity<ProductView>(apiResponse.getBody(), apiResponse.getStatusCode());
            }
            else {
                throw new Exception("[" + apiResponse.getStatusCode().toString() + "] - " +  apiResponse.getBody().toString());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR.toString() + ": " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

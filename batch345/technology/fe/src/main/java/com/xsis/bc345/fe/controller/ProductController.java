package com.xsis.bc345.fe.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.bc345.fe.models.ProductView;
import com.xsis.bc345.fe.models.VariantView;


@Controller
@RequestMapping("/product")
public class ProductController {
    // HTTP Client
    private RestTemplate restTemplate = new RestTemplate();

    // API
    @Value("${application.api.url}")
    private String apiUrl;
    // private final String apiUrl="http://localhost:8080/api/product";

    @GetMapping("")
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("/product/index");
        ResponseEntity<ProductView[]> apiResponse = null; 

        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "/product", ProductView[].class);

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                ProductView[] data = apiResponse.getBody();
                view.addObject("product", data);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }

        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("/product/add");
        ResponseEntity<VariantView[]> apiResponse = null;
        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "/variant", VariantView[].class);

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                VariantView[] data = apiResponse.getBody();
                view.addObject("variant", data);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }

        view.addObject("title", "Create New Product");

        return view;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@ModelAttribute ProductView product){
        ResponseEntity<ProductView> apiResponse = null;
        
        try {
            apiResponse = restTemplate.postForEntity(apiUrl + "/product", product , ProductView.class);
            if (apiResponse.getStatusCode() == HttpStatus.CREATED) {
                return new ResponseEntity<ProductView>(apiResponse.getBody(), HttpStatus.OK);
            } else {
                throw new Exception(apiResponse.getBody().toString() + ": " + apiResponse.getBody().toString());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // private String saveImage(MultipartFile image) throws IOException {
    //     if (image.isEmpty()) {
    //         throw new IOException("File is empty");
    //     }

    //     // Dapatkan ekstensi file
    //     String originalFileName = image.getOriginalFilename();
    //     String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

    //     // Generate nama file unik (untuk menghindari nama file yang sama)
    //     String newFileName = UUID.randomUUID().toString() + extension;

    //     // Path untuk menyimpan file gambar
    //     File filePath = new File(uploadDir, newFileName);

    //     // Simpan file ke disk
    //     image.transferTo(filePath);

    //     return newFileName;  // Kembalikan nama file yang baru
    // }
}

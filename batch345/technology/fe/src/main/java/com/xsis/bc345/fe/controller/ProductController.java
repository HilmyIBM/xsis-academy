package com.xsis.bc345.fe.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
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
    public ResponseEntity<?> create(@ModelAttribute ProductView product, @RequestParam("SetImage") MultipartFile image) {
        try {
            // Menyimpan file gambar jika ada
            if (!image.isEmpty()) {
                String imageName = saveImage(image);  // Anda dapat menggunakan metode untuk menyimpan file, misalnya di disk atau cloud storage
                product.setImage(imageName);  // Menyimpan path gambar ke dalam objek product
            }

            // Kirim objek product ke API atau database
            ResponseEntity<ProductView> apiResponse = restTemplate.postForEntity(apiUrl + "/product", product, ProductView.class);

            if (apiResponse.getStatusCode() == HttpStatus.CREATED) {
                return new ResponseEntity<>(apiResponse.getBody(), HttpStatus.OK);
            } else {
                throw new Exception("Failed to create product: " + apiResponse.getBody().toString());
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    ModelAndView detail(@PathVariable int id) {
        ModelAndView view = new ModelAndView("/product/detail");
        ResponseEntity<ProductView> apiResponse = null;

        try {
            // Mengambil data variant berdasarkan ID
            apiResponse = restTemplate.getForEntity(apiUrl + "/product/id/" + id, ProductView.class);

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                ProductView data = apiResponse.getBody();
                System.out.println(data.getCreateDate());
                view.addObject("product", data);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }

        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        
        view.addObject("title", "Detail Product");
        
        return view;
    }

    @GetMapping("/edit/{id}")
    ModelAndView edit(@PathVariable int id) {
        ModelAndView view = new ModelAndView("/product/edit");
        ResponseEntity<ProductView> apiResponse = null;
        ResponseEntity<List<VariantView>> variantResponse = null;

        try {
            // Mengambil data variant berdasarkan ID
            apiResponse = restTemplate.getForEntity(apiUrl + "/product/id/" + id, ProductView.class);
            // Mengambil semua kategori
            variantResponse = restTemplate.exchange(apiUrl + "/variant", HttpMethod.GET, null, new ParameterizedTypeReference<List<VariantView>>() {});

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                ProductView data = apiResponse.getBody();
                view.addObject("product", data);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }

            if (variantResponse.getStatusCode() == HttpStatus.OK) {
                List<VariantView> variants = variantResponse.getBody();
                view.addObject("variant", variants);  // Menambahkan kategori ke model view
            } else {
                throw new Exception(variantResponse.getStatusCode().toString() + ": " + variantResponse.getBody());
            }

        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        
        view.addObject("title", "Edit Product");
        
        return view;
    }

    @PostMapping("/update")
    ResponseEntity<?> update(@ModelAttribute ProductView product, @RequestParam("SetImage") MultipartFile image, @RequestParam("currentImage") String currentImg){
        ResponseEntity<ProductView> apiResponse = null;

        try {
            // Menyimpan file gambar jika ada
            String imageName = currentImg;
            if (!image.isEmpty()) {
                imageName = saveImage(image);
            }
            product.setImage(imageName);
            restTemplate.put(apiUrl + "/product", product);
            apiResponse = restTemplate.getForEntity(apiUrl + "/product/id/" + product.getId() , ProductView.class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<ProductView>(apiResponse.getBody(), HttpStatus.OK);
            } else {
                throw new Exception(apiResponse.getBody().toString() + ": " + apiResponse.getBody().toString());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable int id){
        ModelAndView view = new ModelAndView("/product/delete");
        view.addObject("id", id);
        view.addObject("title", "Delete Confirmation");
        return view;
    }

    @PostMapping("/delete/{id}/{userId}")
    ModelAndView delete(@PathVariable int id, @PathVariable int userId) {
        ModelAndView view = new ModelAndView("/product/delete");
        ResponseEntity<ProductView> apiResponse = null;
        ProductView product = new ProductView();

        product.setId(id);
        product.setUpdateBy(userId);

        try {
            apiResponse = restTemplate.exchange(
                apiUrl + "/product/delete/" + id + "/" + userId, 
                HttpMethod.DELETE, new HttpEntity<ProductView>(product), 
                ProductView.class
            );
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                ProductView data = apiResponse.getBody();
                view.addObject("product", data);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }

        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        
        view.addObject("title", "Delete Confirmation");
        
        return view;
    }

    private String saveImage(MultipartFile image) throws IOException {
        // Simpan gambar di server atau cloud storage
        // Ini contoh menyimpan di direktori lokal:
        String fileName = UUID.randomUUID().toString() + "." + getExtension(image.getOriginalFilename());
        Path path = Paths.get("src/main/resources/static/assets/img/" + fileName);
        Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        return fileName;  // Mengembalikan path gambar
    }

    private String getExtension(String filename) {
        return filename.substring(filename.lastIndexOf('.') + 1);
    }
}

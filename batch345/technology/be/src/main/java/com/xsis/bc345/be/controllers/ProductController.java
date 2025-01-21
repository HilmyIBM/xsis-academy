package com.xsis.bc345.be.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

// library for handling the file
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import com.xsis.bc345.be.models.Product;
import com.xsis.bc345.be.services.ProductService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@CrossOrigin("*")
@RequestMapping("api/product")
public class ProductController {

    @Autowired
    private ProductService productSvc;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            List<Map<String, Object>> data = productSvc.getAllNative();
            if (data.size() == 0) {
                return new ResponseEntity<List<Product>>(new ArrayList<Product>(), HttpStatus.OK);
            }
            return new ResponseEntity<List<Map<String, Object>>>(data, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> saveProduct(@RequestParam("image") MultipartFile image,
            @RequestParam("name") String name,
            @RequestParam("price") BigDecimal price,
            @RequestParam("stock") int stock,
            @RequestParam("variantId") int variantId) {
        try {
            // Validate the file
            if (image.isEmpty()) {
                return new ResponseEntity<String>("No file uploaded", HttpStatus.BAD_REQUEST);
            }
            if (!image.getContentType().startsWith("image/")) {
                return new ResponseEntity<String>("Invalid file type", HttpStatus.BAD_REQUEST);
            }
            Product newProduct = new Product();
            newProduct.setName(name);
            newProduct.setPrice(price);
            newProduct.setStock(stock);
            newProduct.setVariantId(variantId);

            if (!image.isEmpty()) {
                String imagePath = saveImage(image); // Save the image and get the path
                newProduct.setImage(imagePath); // Store the image path in your product object
                System.out.println(imagePath);
            }
            Product savedProduct = productSvc.create(newProduct);

            return new ResponseEntity<Product>(savedProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String saveImage(MultipartFile image) throws IOException {
        // Make sure the directory exists, or create it
        Path uploadDir = Paths.get("uploads");
        if (!Files.exists(uploadDir)) {
            System.out.println(uploadDir);
            Files.createDirectories(uploadDir);
        }

        // Generate a unique filename (to avoid overwriting files with the same name)
        String originalFileName = image.getOriginalFilename();
        String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;

        // Save the file to the uploads directory
        Path filePath = uploadDir.resolve(uniqueFileName);
        Files.write(filePath, image.getBytes());

        // Return the path of the saved file
        return filePath.toString();
    }
}

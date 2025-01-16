package com.xsis.bc345.be.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllProduct() {
        var data = productService.getAllProduct();

        if (data.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> saveProduct(@RequestBody ProductModel productModel) {
        return new ResponseEntity<>(productService.createProduct(productModel), HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<?> updateProduct(@RequestBody ProductModel productModel) {
        return new ResponseEntity<>(productService.updateProduct(productModel), HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteProduct(@RequestBody ProductModel productModel) {
        productService.deleteProduct(productModel);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package com.xsis.bc345.be.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.xsis.bc345.be.models.Category;
import com.xsis.bc345.be.models.Product;
import com.xsis.bc345.be.repositories.ProductRepository;

@Service
public class ProductService {
    private ProductRepository productRepo;

    public ProductService(ProductRepository productRepo){
        this.productRepo = productRepo;
    }
    
    public List<Product> getAll() {
        try {
            return productRepo.findByDeleted(false).get();
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }
    }
    public List<Map<String, Object>> getAllNative() throws Exception {
        try {
            return productRepo.findAllNative().get();
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }
    }
}

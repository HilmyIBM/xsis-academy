package com.xsis.bc345.be.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.xsis.bc345.be.models.Product;
import com.xsis.bc345.be.repositories.ProductRepository;

@Service
public class ProductService {
    private ProductRepository productRepo;

    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getAll() throws Exception {
        return productRepo.findByDeleted(false).get();
    }

    public List<Map<String,Object>> getAllNative() throws Exception{
        return productRepo.findAllData().get();
    }

    public Product create(Product data) throws Exception {
        return productRepo.save(data);
    }

    public List<Product> getFilter(String filter) throws Exception {
        return productRepo.findByCategoryContainsIgnoreCaseOrNameContainsIgnoreCaseAndDeleted(filter, filter, false).get();
    }
}

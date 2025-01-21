package com.xsis.be.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.xsis.be.models.Product;
import com.xsis.be.repositories.ProductRepository;

@Service
public class ProductService {
    private ProductRepository productRepo;

    public ProductService(ProductRepository productRepo){
        this.productRepo = productRepo;
    }
    public List<Map<String,Object>> getAll() throws Exception{
        return productRepo.findByNativeQuery().get();
    }
}

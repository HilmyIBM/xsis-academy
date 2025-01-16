package com.xsis.bc345.be.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsis.bc345.be.models.Product;
import com.xsis.bc345.be.repositories.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepo;
    
    public List<Product> getAll(){
        try {
            return productRepo.findByDeleted(false).get();
        } catch (Exception e) {
            throw e;
        }
    }
}

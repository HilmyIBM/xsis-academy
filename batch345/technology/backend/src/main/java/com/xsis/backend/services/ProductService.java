package com.xsis.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xsis.backend.models.Product;
import com.xsis.backend.repositories.ProductRepository;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll() throws Exception {
        return productRepository.findByDeleted(false).get();
    }
}

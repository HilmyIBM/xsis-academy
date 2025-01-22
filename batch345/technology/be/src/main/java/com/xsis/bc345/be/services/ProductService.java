package com.xsis.bc345.be.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsis.bc345.be.repositories.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepo;

    public Optional<List<Map<String, Object>>> getAll() {
        // return productRepo.findByDeleted(false).get();
        return productRepo.findAllNative();
    }

    public Optional<Map<String, Object>> getBy(int id) {
        // return productRepo.findByDeleted(false).get();
        return productRepo.findByNative(id);
    }

    public Optional<Map<String, Object>> getBy(String filter) {
        // return productRepo.findByDeleted(false).get();
        return productRepo.findByNative(filter.toLowerCase());
    }
}

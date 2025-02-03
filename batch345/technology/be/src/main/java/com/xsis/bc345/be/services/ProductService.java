package com.xsis.bc345.be.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.xsis.bc345.be.models.Product;
import com.xsis.bc345.be.repositories.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepo;

    private Optional<Product> existingProduct;

    public Optional<List<Map<String, Object>>> getAll() {
        // return productRepo.findByDeleted(false).get();
        return productRepo.findAllNative();
    }

    public Page<Map<String, Object>> getAll(Pageable pageable) {
        return productRepo.findAllNative(pageable);
    }

    public Optional<Map<String, Object>> getBy(int id) {
        // return productRepo.findByDeleted(false).get();
        return productRepo.findByNative(id);
    }

    public List<Map<String, Object>> getBy(String filter) {
        // return productRepo.findByDeleted(false).get();
        return productRepo.findByNative(filter.toLowerCase()).get();
    }

    public Page<Map<String, Object>> getBy(String filter, Pageable pageable) {
        return productRepo.findByNative(filter.toLowerCase(), pageable);
    }

    public Product create(Product data) {
        return productRepo.save(data);
    }

    public Product update(Product data) throws Exception {
        existingProduct = productRepo.findById(data.getId());

        if (existingProduct.isPresent()) {
            data.setCreateBy(existingProduct.get().getCreateBy());
            data.setCreateDate(existingProduct.get().getCreateDate());
            data.setUpdateDate(LocalDateTime.now());

            return productRepo.save(data);
        }
        else {
            throw new Exception("Product with ID = " + data.getId() + " doesn't exist!");
        }
    }

    public Product delete(int id, int userId) throws Exception {
        existingProduct = productRepo.findByIdAndDeleted(id, false);

        if (existingProduct.isPresent()) {
            existingProduct.get().setDeleted(true);
            existingProduct.get().setUpdateBy(userId);
            existingProduct.get().setUpdateDate(LocalDateTime.now());

            return productRepo.save(existingProduct.get());
        }
        else {
            throw new Exception("Variant doesn't exist!");
        }
    }
}

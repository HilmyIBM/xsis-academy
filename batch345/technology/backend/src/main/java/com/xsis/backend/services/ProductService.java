package com.xsis.backend.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.xsis.backend.models.Product;
import com.xsis.backend.repositories.ProductRepository;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private Optional<Product> existingData;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Map<String, Object>> getAll() throws Exception {
        return productRepository.findAllNative().get();
    }

    public Product create(Product data) throws Exception {
        return productRepository.save(data);
    }

    public Map<String, Object> getById(int id) throws Exception {
        Optional<Map<String, Object>> productData = productRepository.findByIdCustom(id);
        if (productData.isPresent()) {
            return productData.get();
        } else {
            throw new Exception("Product with ID " + id + " not found");
        }
    }

    public List<Map<String, Object>> getListByFilter(String filter) throws Exception {
        return productRepository.findByFilter(filter).get();
    }

    public Product update(Product data) throws Exception {
        existingData = productRepository.findById(data.getId());
        if (existingData.isPresent()) {
            Product existingProduct = existingData.get();
            data.setCreateBy(existingProduct.getCreateBy());
            data.setCreateDate(existingProduct.getCreateDate());
            data.setUpdateDate(LocalDateTime.now());
            return productRepository.save(data);
        } else {
            throw new Exception("Product doesn't exist!");
        }
    }

    public Product delete(int id, int userId) throws Exception {
        existingData = productRepository.findById(id);
        if (existingData.isPresent()) {
            existingData.get().setDeleted(true);
            existingData.get().setUpdateBy(userId);
            existingData.get().setUpdateDate(LocalDateTime.now());

            return productRepository.save(existingData.get());
        } else {
            throw new Exception("Product doesn't exist!");
        }
    }
}

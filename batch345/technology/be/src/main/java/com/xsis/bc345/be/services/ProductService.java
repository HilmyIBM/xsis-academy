package com.xsis.bc345.be.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.xsis.bc345.be.models.Product;
import com.xsis.bc345.be.repositories.ProductRepository;

@Service
public class ProductService {
    private ProductRepository productRepo;
    private Optional<Product> existingProduct;

    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getAll() throws Exception {
        return productRepo.findByDeleted(false).get();
    }

    public List<Map<String, Object>> getAllNative() throws Exception {
        return productRepo.findAllNative().get();
    }

    public Page<Map<String, Object>> getAllPaginationFilter(String filter, Pageable page) {
        return productRepo.findByPaginationFilter(filter, page);
    }

    public Page<Map<String, Object>> getAllNativePage(Pageable page) throws Exception {
        return productRepo.findAllNativePage(page);
    }

    public Product create(Product data) throws Exception {
        return productRepo.save(data);
    }

    public Map<String, Object> getById(int id) throws Exception {
        return productRepo.findByNativeQueryId(id).get();
    }

    public List<Map<String, Object>> getFilter(String filter) throws Exception {
        return productRepo.findByFilter(filter).get();
    }

    public Product update(Product data) throws Exception {
        existingProduct = productRepo.findById(data.getId());
        if (existingProduct.isPresent()) {
            data.setCreateBy(existingProduct.get().getCreateBy());
            data.setCreateDate(existingProduct.get().getCreateDate());
            data.setUpdateDate(LocalDateTime.now());
            data.setUpdateBy(data.getUpdateBy());
            return productRepo.save(data);
        }
        throw new Exception("Product doesn't exists");
    }

    public Product delete(int id, int userId) throws Exception {
        existingProduct = productRepo.findById(id);
        if (existingProduct.isPresent()) {
            existingProduct.get().setDeleted(true);
            existingProduct.get().setUpdateBy(userId);
            existingProduct.get().setUpdateDate(LocalDateTime.now());
            return productRepo.save(existingProduct.get());
        } else {
            throw new Exception("Product doesn't exists");
        }
    }
}

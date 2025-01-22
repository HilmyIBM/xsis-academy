package com.xsis.bc345.be.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsis.bc345.be.models.Product;
import com.xsis.bc345.be.repositories.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepo;
    private Optional<Product> existingProduct;

    
    // public List<Product> getAll(){
    //     try {
    //         return productRepo.findByDeleted(false).get();
    //     } catch (Exception e) {
    //         throw e;
    //     }
    // }

    public List<Map<String, Object>> getAll(){
        try {
            return productRepo.findProduct().get();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Map<String, Object>> getByFilter(String filter){
        try {
            return productRepo.findByFilter(filter).get();
        } catch (Exception e) {
            throw e;
        }
    }

    public Product create(Product data) throws Exception{
        return productRepo.save(data);
    }

    public Product delete(int id, int userId) throws Exception {
        existingProduct = productRepo.findById(id);

        if (existingProduct.isPresent()) {
            existingProduct.get().setDeleted(true);
            existingProduct.get().setUpdateBy(userId);
            existingProduct.get().setUpdateDate(LocalDateTime.now());

            return productRepo.save(existingProduct.get());
        } else {
            throw new Exception("Product doesn't exist!");
        }
    }
}

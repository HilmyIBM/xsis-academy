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
import com.xsis.bc345.be.models.Variant;
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

    public Page<Map<String, Object>> getAll(Pageable pageable){
        return productRepo.findAllNative(pageable);
    }

    public List<Map<String, Object>> getBy(String filter) {
        // return productRepo.findByDeleted(false).get();
        return productRepo.findByNative(filter.toLowerCase()).get();
    }

    public Page<Map<String, Object>> getBy(String filter, Pageable pageable) {
        return productRepo.findByNative(filter.toLowerCase(), pageable);
    }


    public Map<String, Object> getByIdNative(int id){
        try {
            return productRepo.findByIdNative(id).get();
        } catch (Exception e) {
            throw e;
        }
    }

    public Product create(Product data) throws Exception{
        return productRepo.save(data);
    }

    public Product update(Product data) throws Exception {
        existingProduct = productRepo.findById(data.getId());
        if(existingProduct.isPresent()){
            data.setCreateBy(existingProduct.get().getCreateBy());
            data.setCreateDate(existingProduct.get().getCreateDate());
            data.setUpdateDate(LocalDateTime.now());

            // Update
            return productRepo.save(data);
        }else{
            throw new Exception("Product doesn't exist!");
        }
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

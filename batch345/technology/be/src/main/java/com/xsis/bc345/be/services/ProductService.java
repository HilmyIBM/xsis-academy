package com.xsis.bc345.be.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.xsis.bc345.be.models.Category;
import com.xsis.bc345.be.models.Product;
import com.xsis.bc345.be.models.Variant;
import com.xsis.bc345.be.repositories.ProductRepository;

@Service
public class ProductService {
    private ProductRepository productRepo;

    public ProductService(ProductRepository productRepo){
        this.productRepo = productRepo;
    }
    
    public List<Product> getAll() {
        try {
            return productRepo.findByDeleted(false).get();
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }
    }

    public Product update(Product data) throws Exception{
       Optional <Product> variantExisting = productRepo.findById(data.getId());
        if (productRepo.findById(data.getId()).isPresent()){
            // update fields
            data.setCreateBy(variantExisting.get().getCreateBy()); 
            data.setCreateDate(variantExisting.get().getCreateDate());
            data.setUpdateDate(LocalDateTime.now());

            // update table
            return productRepo.save(data);
        } else {

            // TODO Auto-generated method stub
            throw new Exception("Variant doesn't exist!");
        }
    }

    public List<Map<String, Object>> getAllNative() throws Exception {
        try {
            return productRepo.findAllNative().get();
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }
    }

    public List<Map<String, Object>> getFilterNative(String filter) throws Exception {
        try {
            return productRepo.findAllFilter(filter).get();
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }
    }

    public Optional<Product> getById(int id, boolean deleted) {
        // TODO Auto-generated method stub
        return productRepo.findByIdAndDeleted(id, deleted);    }

}

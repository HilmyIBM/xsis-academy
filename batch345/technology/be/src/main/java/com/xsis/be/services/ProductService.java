package com.xsis.be.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.xsis.be.models.Product;
import com.xsis.be.models.Variant;
import com.xsis.be.repositories.ProductRepository;

@Service
public class ProductService {
    private ProductRepository productRepo;
    private Optional<Product> exsistingVariant;

    public ProductService(ProductRepository productRepo){
        this.productRepo = productRepo;
    }
    public List<Map<String,Object>> getAll() throws Exception{
        return productRepo.findByNativeQuery().get();
    }
    public Product create(Product product) throws Exception{
        return productRepo.save(product);
    }
    public Map<String,Object> getById(Integer id) throws Exception{
        return productRepo.findIdByNativeQuery(id).get();
    }
    public Product update(Product data) throws Exception{
        exsistingVariant = productRepo.findById(data.getId());
        if(exsistingVariant.isPresent()){
            // update field
            data.setCreateBy(exsistingVariant.get().getCreateBy());
            data.setCreateDate(exsistingVariant.get().getCreateDate());
            data.setUpdateDate(LocalDateTime.now());

            // update table
            return productRepo.save(data);
        }
        throw new Exception("Category doesn't exsist");
    }
}

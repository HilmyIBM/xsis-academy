package com.xsis.be.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.xsis.be.models.Product;
import com.xsis.be.models.Variant;
import com.xsis.be.repositories.ProductRepository;

@Service
public class ProductService {
    private ProductRepository productRepo;
    private Optional<Product> exsistingProduct;

    public ProductService(ProductRepository productRepo){
        this.productRepo = productRepo;
    }
    public List<Map<String,Object>> getAll() throws Exception{
        return productRepo.findByNativeQuery().get();
    }
    public Page<Map<String,Object>> getAll(Pageable pageable) throws Exception{
        return productRepo.findByNativeQuery(pageable);
    }
    
    public List<Map<String,Object>> getAllFilter(String filter) throws Exception{
        return productRepo.findByNativeQueryFilter(filter).get();
    }
    public Page<Map<String,Object>> getAllFilter(String filter, Pageable page) throws Exception{
        return productRepo.findByNativeQueryFilter(filter, page);
    }


    public Product create(Product product) throws Exception{
        return productRepo.save(product);
    }
    public Map<String,Object> getById(Integer id) throws Exception{
        return productRepo.findIdByNativeQuery(id).get();
    }
    public Product update(Product data) throws Exception{
        exsistingProduct = productRepo.findById(data.getId());
        if(exsistingProduct.isPresent()){
            // update field
            data.setCreateBy(exsistingProduct.get().getCreateBy());
            data.setCreateDate(exsistingProduct.get().getCreateDate());
            data.setUpdateDate(LocalDateTime.now());

            // update table
            return productRepo.save(data);
        }
        throw new Exception("Product doesn't exsist");
    }

    public Product delete(int id, int userId) throws Exception{
        exsistingProduct = productRepo.findById(id);
        if(exsistingProduct.isPresent()){
            exsistingProduct.get().setDeleted(true);
            exsistingProduct.get().setUpdateBy(userId);
            exsistingProduct.get().setUpdateDate(LocalDateTime.now());
            return productRepo.save(exsistingProduct.get());
        }
        throw new Exception("Product doesn't exsist");
    }
}

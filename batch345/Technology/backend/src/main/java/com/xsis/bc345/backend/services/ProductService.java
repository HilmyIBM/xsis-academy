package com.xsis.bc345.backend.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsis.bc345.backend.models.CustomerModel;
import com.xsis.bc345.backend.models.ProductModel;
import com.xsis.bc345.backend.repositories.ProductRepo;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productrepo;

    public List<ProductModel> getAll(){
        try {
            return productrepo.findByIsDeleted(false).get();
        } catch (Exception e) {
            throw e;
        }   
    }

    
    public ProductModel create(ProductModel data) throws Exception{
        return productrepo.save(data);
    }

    public ProductModel update(ProductModel data) throws Exception{
        Optional<ProductModel> productexist=productrepo.findById(data.getId());
        if (productexist.isPresent()) {
            data.setCreateBy(productexist.get().getCreateBy());
            data.setCreateDate(productexist.get().getCreateDate());
            data.setUpdateDate(LocalDateTime.now());
            return productrepo.save(data);
        } else {
            throw new Exception("Data Tidak Ada");
        }
    }

}

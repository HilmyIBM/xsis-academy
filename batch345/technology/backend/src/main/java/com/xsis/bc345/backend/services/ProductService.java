package com.xsis.bc345.backend.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.xsis.bc345.backend.models.Product;
import com.xsis.bc345.backend.repositories.ProductRepository;

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
      throw e;
    }
  }

  public Optional<Product> getById(int id) throws Exception {
      return productRepo.findByIdAndDeleted(id, false);
  }

  public List<Map<String, Object>> getAllNative() throws Exception {
    return productRepo.nativeFindAll().get();
  }

  public Optional<Map<String, Object>> getByIdNative(int id) throws Exception {
    return productRepo.nativeFindById(id);
  }
  public Product create(Product data) throws Exception {
    return productRepo.save(data);
  }

  public Product update(Product data) throws Exception {
    Optional<Product> existingProduct = productRepo.findById(data.getId());
    if(existingProduct.isPresent()) {
      data.setCreateBy(existingProduct.get().getCreateBy());
      data.setCreateDate(existingProduct.get().getCreateDate());
      data.setUpdateDate(LocalDateTime.now());

      return productRepo.save(data);
    } else {
      throw new Exception("Product tidak ada");
    }
  }

  public Product delete(int id, int userId) throws Exception {
    Optional<Product> existingProduct = productRepo.findById(id);

    if(existingProduct.isPresent()){
      existingProduct.get().setDeleted(true);
      existingProduct.get().setUpdateBy(userId);
      existingProduct.get().setUpdateDate(LocalDateTime.now());

      return productRepo.save(existingProduct.get());
    }
    else {
      throw new Exception("Product tidak ditemukan");
    }
  }
}

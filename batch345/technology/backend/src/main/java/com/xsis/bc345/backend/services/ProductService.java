package com.xsis.bc345.backend.services;

import java.util.List;

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
}

package com.xsis.batch345.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.xsis.batch345.backend.model.Product;
import com.xsis.batch345.backend.repository.ProductRepository;

@Service
public class ProductService {
  
  private ProductRepository productRepo;

  public ProductService( ProductRepository productRepo ) {
    this.productRepo = productRepo;
  }

  public Optional<List<Product>> findAll() {
    return productRepo.nativeFindAll();
  }

}

package com.xsis.bc345.backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xsis.bc345.backend.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
  Optional<List<Product>> findByDeleted(boolean deleted); 
}

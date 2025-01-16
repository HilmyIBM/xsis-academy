package com.xsis.bc345.be.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xsis.bc345.be.models.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    Optional<List<Product>> findByDeleted(boolean deleted);
} 

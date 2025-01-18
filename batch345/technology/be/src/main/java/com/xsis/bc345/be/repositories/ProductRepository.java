package com.xsis.bc345.be.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xsis.bc345.be.models.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByIdAndDeleted(int id, boolean deleted);
    Optional<List<Product>> findByDeleted(boolean deleted);
    Optional<List<Product>> findByNameIgnoreCase(String variantName);
    Optional<List<Product>> findByNameContainsIgnoreCase(String variantName);
    Optional<List<Product>> findByNameContainsIgnoreCaseAndDeleted(String variantName, boolean deleted);
}

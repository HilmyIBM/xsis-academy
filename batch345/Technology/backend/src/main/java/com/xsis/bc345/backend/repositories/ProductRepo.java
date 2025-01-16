package com.xsis.bc345.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xsis.bc345.backend.models.ProductModel;
import java.util.List;
import java.util.Optional;


@Repository
public interface  ProductRepo extends JpaRepository<ProductModel,Integer> {
    Optional<List<ProductModel>> findByIsDeleted(boolean isDeleted);
}

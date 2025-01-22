package com.xsis.bc345.be.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    Optional<List<ProductModel>> findAllByDeletedAndVariant_Deleted(boolean deleted, boolean variantDeleted);

    Page<ProductModel> findAllByDeleted(boolean deleted, Pageable pageable);

    Optional<ProductModel> findByIdAndDeleted(long id, boolean deleted);

    int countAllByVariant_DeletedAndId(boolean variantDeleted, long id);
}

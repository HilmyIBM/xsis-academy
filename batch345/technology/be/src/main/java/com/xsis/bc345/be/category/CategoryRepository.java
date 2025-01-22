package com.xsis.bc345.be.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, Integer> {

    Optional<List<CategoryModel>> findAllByDeleted(boolean deleted);

    Optional<CategoryModel> findByIdAndDeleted(int id, boolean deleted);

    Page<CategoryModel> findAllByDeleted(boolean deleted, Pageable pageable);

    Optional<List<CategoryModel>> findAllByCategoryNameLikeIgnoreCaseOrDescriptionLikeIgnoreCaseAndDeleted(String categoryName, String description, boolean deleted);

    Optional<List<CategoryModel>> findAllByDescriptionLikeIgnoreCase(String description);

    Optional<List<CategoryModel>> findAllByCategoryNameLikeIgnoreCase(String categoryName);
}

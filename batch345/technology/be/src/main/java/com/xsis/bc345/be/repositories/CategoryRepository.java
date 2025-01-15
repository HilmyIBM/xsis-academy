package com.xsis.bc345.be.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xsis.bc345.be.models.Category;
import java.util.List;

@Repository
// <Category,Integer> adalah Model dan tipe dari Primary key dari model tersebut
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // Optional jika 'category' tidak ada datanya, maka dia akan null
    Optional<List<Category>> findByDeleted(boolean deleted);

    Optional<Category> findByIdAndDeleted(int id, boolean deleted);

    // method harus disamakan dengan nama dari nama property di model (contoh filter
    // 2 table. bisa juga dipakai And)
    // Case Sensitive
    Optional<List<Category>> findByCategoryNameContainsIgnoreCase(String categoryName);

    // Filtering the 2 column
    Optional<List<Category>> findByCategoryNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndDeleted(String categoryName,
            String description, boolean deleted);

    // 'like clause' using 'Containing'
}

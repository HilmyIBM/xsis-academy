package com.xsis.bc345.be.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xsis.bc345.be.models.Category;
import com.xsis.bc345.be.models.Variant;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Integer>  {
    Optional<List<Variant>> findByDeleted(boolean deleted);
    // @Query("SELECT v FROM Variant v LEFT JOIN FETCH v.category")
    // List<Variant> findAllVariantsWithCategoryAndDeleted(boolean deleted);
    Optional<Variant> findByIdAndDeleted(int id, boolean deleted);
    Optional<List<Variant>> findByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCaseAndDeleted(String name, String description, boolean deleted);

}

package com.xsis.bc345.be.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, Integer> {

    List<CategoryModel> findAllByDeleted(boolean deleted);

}

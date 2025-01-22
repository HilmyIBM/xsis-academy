package com.xa.pos313.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xa.pos313.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}

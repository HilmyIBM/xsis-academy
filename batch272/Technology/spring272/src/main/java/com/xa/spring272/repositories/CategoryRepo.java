package com.xa.spring272.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xa.spring272.models.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

}

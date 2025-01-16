package com.xa.spring272.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xa.spring272.models.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long>  {
	
	@Query(value="SELECT * FROM products p WHERE upper(p.name) LIKE %:key% "
			+ "OR upper(p.description) LIKE %:key%", nativeQuery=true)
	List<Product> searchProduct(@Param("key") String key);
	
}

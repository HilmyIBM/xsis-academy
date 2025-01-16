package com.xa.spring272.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xa.spring272.models.Variant;

@Repository
public interface VariantRepo extends JpaRepository<Variant, Long> {

	@Query(value="SELECT * FROM variants v WHERE v.category_id = ?1", nativeQuery=true)
	List<Variant> getVariantByCategory(Long CategoryId);
	
}

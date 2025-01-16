package com.xa.spring272.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xa.spring272.models.LokasiLevel;



@Repository
public interface LokLevRepo extends JpaRepository<LokasiLevel, Long> {
	
	@Query(value="select * from m_location_level m where m.is_delete=false", nativeQuery=true)
	List<LokasiLevel>FindByDelete();
}

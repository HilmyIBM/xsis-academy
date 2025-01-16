package com.xa.spring272.repositories;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xa.spring272.models.Lokasi;


@Repository
public interface LokRepo extends JpaRepository<Lokasi, Long> {
	@Query(value="SELECT "
			+ "	l.id, "
			+ "	l.location_id, "
			+ "	l.name, "
			+ "	concat(l.name, ' - ', (SELECT name FROM m_location WHERE id=l.location_id)) AS wilayah "
			+ "FROM m_location l "
			+ "LEFT JOIN m_location ll on l.id=ll.location_id "
			+ "WHERE l.location_id IS NOT NULL", nativeQuery=true)
	List<Map<String, Object>> AllLocation();
	
	@Query(value="SELECT * FROM m_location l WHERE l.location_id=?1", nativeQuery = true)
	List<Lokasi> getLokasi(Long location_id);
	

}

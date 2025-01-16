package com.xa.spring272.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xa.spring272.models.OrderDetail;

@Repository
public interface OrderDetailRepo extends JpaRepository<OrderDetail, Long> {

	@Query(value="SELECT * FROM order_details d WHERE d.header_id=?1",
			nativeQuery=true)
	List<OrderDetail> getDetailByHid(Long headerId);
	
}
 
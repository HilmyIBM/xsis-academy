package com.xa.spring272.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xa.spring272.models.OrderHeader;

@Repository
public interface OrderHeaderRepo extends JpaRepository<OrderHeader, Long>{

	@Transactional
	@Modifying
	@Query(value="UPDATE order_headers oh SET oh.amount = ?1 WHERE id=?2",
			nativeQuery=true)
	OrderHeader updateAmount(Double amount, Long id);
}

package com.xsis.backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xsis.backend.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<List<Customer>> findByDeleted(boolean deleted);

    Optional<Customer> findByIdAndDeleted(int id, boolean deleted);

    @Query(value = """
                SELECT
                    c.id, c.name, c.address, c.email, c.password, c.phone, c.role_id,
                    c.is_deleted, c.create_by, c.create_date, c.update_by, c.update_date
                FROM tbl_m_customer AS c
                WHERE (c.is_deleted IS FALSE) AND (LOWER(c.name) LIKE LOWER(CONCAT('%', :filter, '%')) OR
                    LOWER(c.email) LIKE LOWER(CONCAT('%', :filter, '%')) OR
                    LOWER(c.phone) LIKE LOWER(CONCAT('%', :filter, '%')))
            """, nativeQuery = true)
    Optional<List<Customer>> findNativeByFilter(@Param("filter") String filter);
}

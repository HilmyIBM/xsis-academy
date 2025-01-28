package com.xsis.backend.repositories;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xsis.backend.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<List<Customer>> findByDeleted(boolean deleted);

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByIdAndDeleted(int id, boolean deleted);

    @Query(value = """
                SELECT
                    c.id, c.name, c.address, c.email, c.phone, c.role_id AS "roleId",
                    c.is_deleted AS "deleted", c.create_by AS "createBy", c.create_date AS "createDate",
                    c.update_by AS "updateBy", c.update_date AS "updateDate"
                FROM tbl_m_customer AS c
                WHERE (c.is_deleted IS FALSE) AND (LOWER(c.name) LIKE LOWER(CONCAT('%', :filter, '%')) OR
                    LOWER(c.email) LIKE LOWER(CONCAT('%', :filter, '%')) OR
                    LOWER(c.phone) LIKE LOWER(CONCAT('%', :filter, '%')))
            """, nativeQuery = true)
    Optional<List<Map<String, Object>>> findNativeByFilter(@Param("filter") String filter);
}

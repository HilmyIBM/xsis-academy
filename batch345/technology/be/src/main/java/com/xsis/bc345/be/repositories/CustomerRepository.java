package com.xsis.bc345.be.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xsis.bc345.be.models.Customer;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<List<Customer>> findByDeleted(boolean deleted);
    Optional<Customer> findByIdAndDeleted(int id, boolean isDeleted);
    Optional<Customer> findByEmailAndDeleted(String email, boolean isDeleted);

    @Query(
        nativeQuery = true,
        value = """
            SELECT
                id, name, password, address, phone, email, role_id as "roleId", is_deleted AS "deleted"
                , create_by AS "createBy", create_date AS "createDate", update_by AS "updateBy", update_date AS "updateDate"
            FROM tbl_m_customer
            WHERE is_deleted = false
        """
    )
    Optional<List<Map<String, Object>>> findAllNative();

    @Query(
        nativeQuery = true,
        value = """
            SELECT
                id, name, password, address, phone, email, role_id as "roleId", is_deleted AS "deleted"
                , create_by AS "createBy", create_date AS "createDate", update_by AS "updateBy", update_date AS "updateDate"
            FROM tbl_m_customer
            WHERE is_deleted = false AND id = ?1
        """
    )
    Optional<Map<String, Object>> findByIdNative(@Param("id") int id);

    @Query(
        nativeQuery = true,
        value = """
            SELECT
                id, name, password, address, phone, email, role_id as "roleId", is_deleted AS "deleted"
                , create_by AS "createBy", create_date AS "createDate", update_by AS "updateBy", update_date AS "updateDate"
            FROM tbl_m_customer
            WHERE is_deleted = false AND (
                LOWER(name) LIKE %?1%
                OR LOWER(address) LIKE %?1%
                OR LOWER(phone) LIKE %?1%
                OR LOWER(email) LIKE %?1%
            )
        """
    )
    Optional<List<Map<String, Object>>> findByFilterNative(String filter);
}
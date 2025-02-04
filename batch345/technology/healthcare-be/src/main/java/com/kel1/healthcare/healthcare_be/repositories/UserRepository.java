package com.kel1.healthcare.healthcare_be.repositories;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kel1.healthcare.healthcare_be.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
    @Query(
        nativeQuery = true,
        value = """
            SELECT
                id, biodata_id as biodataId, role_id as roleId, email, password, login_attempt as loginAttempt, is_locked as isLocked, last_login as lastLogin, created_by as createdBy, created_on as createdOn, modified_by as modifiedBy, modified_on as modifiedOn, deleted_by as deletedBy, deleted_on as deletedOn, is_delete AS "deleted" 
            FROM m_user
            WHERE is_delete = false
        """
    )
    Optional<List<Map<String, Object>>> findAllNative();
}

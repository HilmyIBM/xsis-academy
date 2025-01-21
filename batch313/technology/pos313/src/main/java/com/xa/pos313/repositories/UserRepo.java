package com.xa.pos313.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xa.pos313.models.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {

	@Query(value="SELECT * FROM users u WHERE u.UserName = ?1 AND u.Password = ?2", 
			nativeQuery=true)
	List<Users> getLogin(String UserName, String Password);

}

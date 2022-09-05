package com.transport.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.transport.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

	@Query(nativeQuery = true, value = "select * from testuser where telephonenumber = :telephoneNumber")
	User findUserByTelephoneNumber(@Param("telephoneNumber") String telephoneNumber);
	
}

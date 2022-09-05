package com.transport.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;

import com.transport.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

	@Query(nativeQuery = true, value = "select * from user where telephonenumber = :telephoneNumber")
	User findUserByTelephoneNumber(@PathVariable("telephoneNumber") String telephoneNumber);
	
}

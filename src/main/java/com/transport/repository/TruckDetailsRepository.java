package com.transport.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.transport.entities.TruckDetails;

public interface TruckDetailsRepository extends CrudRepository<TruckDetails, Long > {

	@Query(nativeQuery = true, value = "select * from truckdetails td where td.vehicalnumber = :vehicalNumber ")
	TruckDetails findByVehicalNumber(@Param("vehicalNumber") String vehicalNumber);
	
}

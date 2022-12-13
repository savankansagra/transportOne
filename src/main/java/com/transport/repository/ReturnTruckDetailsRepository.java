package com.transport.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.transport.entities.ReturnTruckDetails;
import com.transport.entities.TruckDetails;

public interface ReturnTruckDetailsRepository extends CrudRepository<ReturnTruckDetails, String> {
	
	/** return the already existing truck details using truck number. */
	@Query(nativeQuery = true, value = "select * from returntruckdetails rt where rt.vehicle_number = :vehicalNumber ")
	ReturnTruckDetails findByVehicalNumber(@Param("vehicalNumber") String vehicalNumber);
	
}

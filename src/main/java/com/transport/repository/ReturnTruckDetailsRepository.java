package com.transport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.transport.entities.ReturnTruckDetails;
import com.transport.entities.TruckDetails;

public interface ReturnTruckDetailsRepository extends CrudRepository<ReturnTruckDetails, String> {
	
	/** return the already existing truck details using truck number. */
	@Query(nativeQuery = true, value = "select * from return_truck_details rt where rt.vehicle_number = :vehicalNumber ")
	ReturnTruckDetails findByVehicalNumber(@Param("vehicalNumber") String vehicalNumber);
	
	/** return all the user by user */
	@Query(nativeQuery=true, value="select * from return_truck_details rt where rt.user_id = :userId")
	List<ReturnTruckDetails> findAllReturnTruckByUserId(@Param("userId") long userId);
	
}

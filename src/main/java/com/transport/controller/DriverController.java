package com.transport.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transport.entities.ReturnTruckDetails;
import com.transport.entities.TruckDetails;
import com.transport.payloads.NewReturnTruckDetail;
import com.transport.payloads.TruckDetailsDto;
import com.transport.services.DriverService;

@RestController
@RequestMapping(path="/api/driver/")
public class DriverController {

	Logger logger = LoggerFactory.getLogger(DriverController.class);
	
	@Autowired
	DriverService driverService;
	
	@PostMapping(path = "/posttruckdetails")
	public ResponseEntity<TruckDetails> postTruckDetails(@RequestBody TruckDetailsDto truckDetailsDto, Authentication authentication){

		//System.out.println(authentication.getPrincipal());
		
		TruckDetails postedTruckDetails = driverService.postTruckDetails(truckDetailsDto, authentication);
		return new ResponseEntity<TruckDetails>(postedTruckDetails, HttpStatus.CREATED);
	}
	
	
	/**
	 * Posting new return truck details. 
	 * 
	 */
	@PostMapping(path = "/truck")
	public ResponseEntity<ReturnTruckDetails> postNewTruckDetails(@RequestBody NewReturnTruckDetail newReturnTruckDetail, Authentication authentication){
		if(newReturnTruckDetail == null) {
			return ResponseEntity.badRequest().build();
		}
		
		// call the services.
		String principalUser = authentication.getPrincipal().toString();
		ReturnTruckDetails postedReturnTruckDetails = driverService.postReturnTruckDetails(newReturnTruckDetail, authentication);
		
		// return ResponseEntity.status(HttpStatus.CREATED).body(postedReturnTruckDetails);
		return new ResponseEntity<ReturnTruckDetails>(postedReturnTruckDetails, HttpStatus.CREATED);
	}
	
	/**
	 * Get all truck details associated with user id.
	 * 
	 */
	@GetMapping(path = "/truck")
	public ResponseEntity<List<ReturnTruckDetails>> getAllReturnTruckDetails(Authentication authentication){
		 List<ReturnTruckDetails> allReturnTruckDetails = driverService.getAllReturnTruckDetails(authentication);
		 return new ResponseEntity<>(allReturnTruckDetails, HttpStatus.OK);
	}
	
	
}

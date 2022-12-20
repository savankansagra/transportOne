package com.transport.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.transport.controller.DriverController;
import com.transport.dtoOperation.TruckDetailDtoOperation;
import com.transport.entities.ReturnTruckDetails;
import com.transport.entities.User;
import com.transport.payloads.NewReturnTruckDetail;
import com.transport.payloads.TruckDetailsDto;


@Service
public class DriverService {

	Logger logger = LoggerFactory.getLogger(DriverController.class);
	
	@Autowired
	private TruckDetailDtoOperation truckDetailDtoOperation;
	
	@Autowired
	private UserService userService;
	
	
	public ReturnTruckDetails postReturnTruckDetails(NewReturnTruckDetail newReturnTruckDetail, Authentication authentication) {
		logger.info("method=postReturnTruckDetails | newReturnTruckDetail: "+ newReturnTruckDetail + " & authentication: "+authentication);
		User user = userService.findUserByTelephoneNumber(authentication);
		return truckDetailDtoOperation.postReturnTruckDetails(newReturnTruckDetail, user);
		
	}

	public List<ReturnTruckDetails> getAllReturnTruckDetails(Authentication authentication) {
		logger.info("method=getAllReturnTruckDetails | authentication: "+authentication);
		User user = userService.findUserByTelephoneNumber(authentication);
		return truckDetailDtoOperation.getAllReturnTruckDetails(user);
	}
	
	
	
	
}

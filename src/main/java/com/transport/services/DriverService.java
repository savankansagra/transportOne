package com.transport.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.transport.dtoOperation.TruckDetailDtoOperation;
import com.transport.entities.ReturnTruckDetails;
import com.transport.entities.TruckDetails;
import com.transport.entities.User;
import com.transport.payloads.NewReturnTruckDetail;
import com.transport.payloads.TruckDetailsDto;


@Service
public class DriverService {

	@Autowired
	private TruckDetailDtoOperation truckDetailDtoOperation;
	
	@Autowired
	private UserService userService;
	
	public TruckDetails postTruckDetails(TruckDetailsDto truckDetailsDto, Authentication authentication) {
		User user = userService.findUserByTelephoneNumber(authentication);
		return truckDetailDtoOperation.postTruckDetails(truckDetailsDto, user);
	}

	public ReturnTruckDetails postReturnTruckDetails(NewReturnTruckDetail newReturnTruckDetail, Authentication authentication) {
		User user = userService.findUserByTelephoneNumber(authentication);
		return truckDetailDtoOperation.postReturnTruckDetails(newReturnTruckDetail, user);
		
	}

	public List<ReturnTruckDetails> getAllReturnTruckDetails(Authentication authentication) {
		User user = userService.findUserByTelephoneNumber(authentication);
		return truckDetailDtoOperation.getAllReturnTruckDetails(user);
	}
	
	
	
	
}

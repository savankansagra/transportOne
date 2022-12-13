package com.transport.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transport.dtoOperation.TruckDetailDtoOperation;
import com.transport.entities.ReturnTruckDetails;
import com.transport.entities.TruckDetails;
import com.transport.payloads.NewReturnTruckDetail;
import com.transport.payloads.TruckDetailsDto;
import com.transport.repository.TruckDetailsRepository;
import com.transport.util.dto.TruckDtoConverter;

@Service
public class DriverService {

	@Autowired
	private TruckDetailDtoOperation truckDetailDtoOperation;
	
	public TruckDetails postTruckDetails(TruckDetailsDto truckDetailsDto, String userTelephoneNumber) {
		return truckDetailDtoOperation.postTruckDetails(truckDetailsDto, userTelephoneNumber);
	}

	public ReturnTruckDetails postReturnTruckDetails(NewReturnTruckDetail newReturnTruckDetail, String principalUser) {
		return truckDetailDtoOperation.postReturnTruckDetails(newReturnTruckDetail, principalUser);
		
	}
	
	
	
	
}

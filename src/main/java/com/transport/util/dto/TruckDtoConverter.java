package com.transport.util.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.transport.entities.TruckDetails;
import com.transport.entities.User;
import com.transport.payloads.TruckDetailsDto;
import com.transport.repository.TruckDetailsRepository;

@Component
public class TruckDtoConverter {

	@Autowired
	private TruckDetailsRepository truckDetailsRepository;
	
	public TruckDetails convertDtoToPersistanceOb(TruckDetailsDto truckDetailsDto, User user) {
		TruckDetails truckDetails = truckDetailsRepository.findByVehicalNumber(truckDetailsDto.getVehicalNumber());
		if(truckDetails == null) {
			//create Object and return it.
			TruckDetails newTruckDetails = new TruckDetails(
					truckDetailsDto.getTruckType(),
					truckDetailsDto.getTruckLength(),
					truckDetailsDto.getTruckCompany(),
					truckDetailsDto.getTruckCapacity(),
					truckDetailsDto.getVehicalNumber(),
					user);
			
			return newTruckDetails;
		}
		
		return truckDetails;
	}

}

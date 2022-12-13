package com.transport.util.dto;

import org.jboss.logging.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.transport.entities.ReturnTruckDetails;
import com.transport.entities.TruckDetails;
import com.transport.entities.User;
import com.transport.payloads.NewReturnTruckDetail;
import com.transport.payloads.TruckDetailsDto;
import com.transport.repository.ReturnTruckDetailsRepository;
import com.transport.repository.TruckDetailsRepository;

@Component
public class TruckDtoConverter {

	@Autowired
	private TruckDetailsRepository truckDetailsRepository;

	
	@Autowired
	private ReturnTruckDetailsRepository returnTruckDetailsRepository;
	
	
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

	
	public ReturnTruckDetails convertReturnTruckDetailsDtoTopersistent(NewReturnTruckDetail newReturnTruckDetail, User user) {
		ReturnTruckDetails answerReturnTruckDetailsObject;
		
		//check if return truck details already exists.
		ReturnTruckDetails returnTruckDetailsFounded = returnTruckDetailsRepository.findByVehicalNumber(newReturnTruckDetail.getTruckNumber());
		if (returnTruckDetailsFounded == null){
			// new return truck would be created.
			answerReturnTruckDetailsObject = new ReturnTruckDetails(newReturnTruckDetail, user);	
		} else {
			// TODO response with appropriate status code. because system already contain the return truck information.
			System.out.println("return truck with vehicle number already exists in system.");
			answerReturnTruckDetailsObject = returnTruckDetailsFounded.updateReturnTruckDetails(newReturnTruckDetail);
		}
		
		return answerReturnTruckDetailsObject;
	}
	
	
}

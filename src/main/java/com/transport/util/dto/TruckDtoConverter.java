package com.transport.util.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.transport.entities.ReturnTruckDetails;
import com.transport.entities.User;
import com.transport.payloads.NewReturnTruckDetail;
import com.transport.repository.ReturnTruckDetailsRepository;

@Component
public class TruckDtoConverter {

	
	@Autowired
	private ReturnTruckDetailsRepository returnTruckDetailsRepository;

	
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

package com.transport.dtoOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.stereotype.Component;

import com.transport.entities.ReturnTruckDetails;
import com.transport.entities.TruckDetails;
import com.transport.entities.User;
import com.transport.payloads.NewReturnTruckDetail;
import com.transport.payloads.TruckDetailsDto;
import com.transport.repository.ReturnTruckDetailsRepository;
import com.transport.repository.TruckDetailsRepository;
import com.transport.repository.UserRepository;
import com.transport.util.dto.TruckDtoConverter;

@Component
public class TruckDetailDtoOperation {

	@Autowired
	private TruckDetailsRepository truckDetailRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	TruckDtoConverter truckDtoConverter;
	
	@Autowired
	private ReturnTruckDetailsRepository returnTruckDetailsRepository;
	
	
	
	public TruckDetails postTruckDetails(TruckDetailsDto truckDetailsDto, String userTelephoneNumber) {
		// get the user Object of signIn.
		User user = userRepository.findUserByTelephoneNumber(userTelephoneNumber);
		
		//create the persistence object
		TruckDetails truckDetails = truckDtoConverter.convertDtoToPersistanceOb(truckDetailsDto, user);
		//truckDetails
		//save to database.
		TruckDetails savedTruckDetails = truckDetailRepository.save(truckDetails);
						
		return savedTruckDetails;
	}

	
	/**
	 * store entity in database. if already exists then update it.
	 * 
	 * @param newReturnTruckDetail
	 * @param principalUserTelephone
	 * @return
	 */
	public ReturnTruckDetails postReturnTruckDetails(NewReturnTruckDetail newReturnTruckDetail, String principalUserTelephone) {
		// get the user details.
		User user = userRepository.findUserByTelephoneNumber(principalUserTelephone);
		
		// create the persistence object of request.
		ReturnTruckDetails returnTruckDetails = truckDtoConverter.convertReturnTruckDetailsDtoTopersistent(newReturnTruckDetail, user);
		
		// save object to database
		ReturnTruckDetails savedReturnTruckDetails = returnTruckDetailsRepository.save(returnTruckDetails);
		
		return savedReturnTruckDetails;
	}
}

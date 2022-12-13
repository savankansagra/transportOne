package com.transport.dtoOperation;

import java.util.List;

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
	private TruckDtoConverter truckDtoConverter;
	
	@Autowired
	private ReturnTruckDetailsRepository returnTruckDetailsRepository;
	
	
	
	public TruckDetails postTruckDetails(TruckDetailsDto truckDetailsDto, User user) {
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
	 * @param user
	 * @return
	 */
	public ReturnTruckDetails postReturnTruckDetails(NewReturnTruckDetail newReturnTruckDetail, User user) {
		// create the persistence object of request.
		ReturnTruckDetails returnTruckDetails = truckDtoConverter.convertReturnTruckDetailsDtoTopersistent(newReturnTruckDetail, user);
		
		// save object to database
		ReturnTruckDetails savedReturnTruckDetails = returnTruckDetailsRepository.save(returnTruckDetails);
		
		return savedReturnTruckDetails;
	}


	/**
	 * return the list of all return truck associated with user.
	 * 
	 * @param user
	 * @return
	 */
	public List<ReturnTruckDetails> getAllReturnTruckDetails(User user) {
		long userId = user.getId();
		return returnTruckDetailsRepository.findAllReturnTruckByUserId(userId);
	}
	
	
	
	
	
}

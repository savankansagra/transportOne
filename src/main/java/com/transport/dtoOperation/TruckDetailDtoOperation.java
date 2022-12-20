package com.transport.dtoOperation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.transport.controller.DriverController;
import com.transport.entities.ReturnTruckDetails;
import com.transport.entities.User;
import com.transport.payloads.NewReturnTruckDetail;
import com.transport.repository.ReturnTruckDetailsRepository;
import com.transport.util.dto.TruckDtoConverter;

@Component
public class TruckDetailDtoOperation {
	
	Logger logger = LoggerFactory.getLogger(DriverController.class);

	@Autowired
	private TruckDtoConverter truckDtoConverter;
	
	@Autowired
	private ReturnTruckDetailsRepository returnTruckDetailsRepository;

	
	/**
	 * store entity in database. if already exists then update it.
	 * 
	 * @param newReturnTruckDetail
	 * @param user
	 * @return
	 */
	public ReturnTruckDetails postReturnTruckDetails(NewReturnTruckDetail newReturnTruckDetail, User user) {
		logger.info("method=postReturnTruckDetails | newReturnTruckDetail: "+ newReturnTruckDetail + "& user"+user);				
				
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
		logger.info("method=getAllReturnTruckDetails | user"+user);				
		long userId = user.getId();
		return returnTruckDetailsRepository.findAllReturnTruckByUserId(userId);
	}
	
	
}

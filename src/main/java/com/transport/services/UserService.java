package com.transport.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transport.entities.User;
import com.transport.payloads.UserRequestRegister;
import com.transport.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	// Save the new entry to database.
	public boolean save(UserRequestRegister userRequestRegister) {
		boolean isSaved = false;
		User u = userRepository.findUserByTelephoneNumber(userRequestRegister.getTelephoneNumber());
		
		if(u == null) {
			User user = new User();
			user.setTelephoneNumber(userRequestRegister.getTelephoneNumber());
			user.setUserEmail(userRequestRegister.getUserEmail());
			userRepository.save(user);
			isSaved = true;
		}
		
		return isSaved;
	}
	
	public String getTelephoneNumberFromEmail(String email) {
		return userRepository.getTelephoneNumberFromEmail(email);
	}
}

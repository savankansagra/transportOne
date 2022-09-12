package com.transport.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transport.entities.User;
import com.transport.entities.UserRoles;
import com.transport.payloads.LoginEmail;
import com.transport.payloads.LoginTelephoneNumber;
import com.transport.payloads.UserRequestRegister;
import com.transport.repository.UserRepository;
import com.transport.security.JwtTokenProvider;
import com.transport.util.ValidationHelper;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ValidationHelper validationHelper;
	
	@Autowired
	OtpService otpService;
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	
	// Save the new entry to database.
	public boolean save(UserRequestRegister userRequestRegister) {
		boolean isSaved = false;
		User u = userRepository.findUserByTelephoneNumber(userRequestRegister.getTelephoneNumber());
		
		if(u == null) {
			User user = new User();
			user.setTelephoneNumber(userRequestRegister.getTelephoneNumber());
			user.setUserEmail(userRequestRegister.getUserEmail());
			user.setAppUserRoles(userRequestRegister.getUserRoles());
			userRepository.save(user);
			isSaved = true;
		}
		
		return isSaved;
	}
	
	public String getTelephoneNumberFromEmail(String email) {
		return userRepository.getTelephoneNumberFromEmail(email);
	}

	public Boolean isEmailExists(LoginEmail loginEmail) {
		boolean isExists = false;
		boolean isValid = false;
		
		//Check email is valid.
		if(loginEmail != null && loginEmail.getEmailAddress() != null) {
			isValid = validationHelper.isValidEmail(loginEmail.getEmailAddress());	
		}
		
		if(isValid == true) {
			User u = userRepository.findUserByEmailAddress(loginEmail.getEmailAddress());
			if(u != null) {
				isExists = true;
			}
		}
		
		if(isValid == true && isExists == true) {
			return true;
		} else {
			return false;
		}
	}

	public String validateAndSendOtpToEmail(LoginEmail loginEmail) {
		String message = "";
		
		// Is email is valid
		boolean isValid = false;
		if(loginEmail != null && loginEmail.getEmailAddress() != null) {
			isValid = validationHelper.isValidEmail(loginEmail.getEmailAddress());	
		}
		
		// Is email already Exists in database.
		boolean isExists = false;
		if(isValid == true) {
			User u = userRepository.findUserByEmailAddress(loginEmail.getEmailAddress());
			if(u != null) {
				isExists = true;
			}
		}
		
		// send OTP to email address.
		if(isExists == true && isValid == true) {
			otpService.generateAndSendtoEmail(loginEmail.getEmailAddress());
			message = "Otp was send to email.";
		} else if(isValid == true && isExists == false) {
			message = "Email is not found. Please signup the email";
		} else if(isValid == false) {
			message = "email address is not valid. Please enter valid email Address.";
		}
		
		return message;
	}

	
	public String validateAndSendOtpToTelephoneNumber(LoginTelephoneNumber loginTelephoneNumber) {
		String message = "";
		
		// is telephoneNumber is valid
		boolean isValid = false;
		if(loginTelephoneNumber != null && loginTelephoneNumber.getTelephoneNumber() != null) {
			isValid = validationHelper.isValidTelephoneNumber(loginTelephoneNumber.getTelephoneNumber());
		}
		
		// Is telephoneNumber already exists in database.
		boolean isExists = false;
		if(isValid == true) {
			User u = userRepository.findUserByTelephoneNumber(loginTelephoneNumber.getTelephoneNumber());
			if(u != null) {
				isExists = true;
			}
		}
		
		// send OTP to telephone number.
		if(isExists == true && isValid == true) {
			otpService.generateAndSendtoTelephoneNumber(loginTelephoneNumber.getTelephoneNumber());
			message = "Otp was send to telephone number.";
		} else if(isValid == true && isExists == false) {
			message = "Telephone number is not found. Please signup with telephone number.";
		} else if(isValid == false) {
			message = "telephone number is not valid. Please enter valid telephone number.";
		}
		
		return message;
	}

	
	public String createToken(String userTelephoneNumber) {
		return jwtTokenProvider.createTokenWithTelephoneNumberAndRole(userTelephoneNumber,
				userRepository.findUserByTelephoneNumber(userTelephoneNumber).getAppUserRoles());
	}

	public void delete(String telephoneNumber) {
		userRepository.deleteByTelephoneNumber(telephoneNumber);
	}

	public String refresh(String remoteUser) {
		return jwtTokenProvider.createTokenWithTelephoneNumberAndRole(remoteUser, userRepository.findUserByTelephoneNumber(remoteUser).getAppUserRoles());
	}

	public User whoami(HttpServletRequest req) {
		String token = jwtTokenProvider.resolveToken(req);
		String userTelephoneNumber = jwtTokenProvider.getTelephoneNumberFromToken(token);
		User user = userRepository.findUserByTelephoneNumber(userTelephoneNumber);
		return user;
		
	}
	
}

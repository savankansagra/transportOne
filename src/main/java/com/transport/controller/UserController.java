package com.transport.controller;


import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.transport.entities.User;
import com.transport.payloads.LoginEmail;
import com.transport.payloads.LoginTelephoneNumber;
import com.transport.payloads.StandardResponse;
import com.transport.payloads.SuccessToken;
import com.transport.payloads.UserLogin;
import com.transport.payloads.UserLoginEmail;
import com.transport.payloads.UserLoginTelephoneNumber;
import com.transport.payloads.UserRequestRegister;
import com.transport.security.JwtTokenProvider;
import com.transport.services.OtpService;
import com.transport.services.UserService;


/**
 * Detailed Explanation :

 @RestController : First of all, we are using Spring 4′s new @RestController annotation. This annotation eliminates the need of annotating each method with @ResponseBody. Under the hood, @RestController is itself annotated with @ResponseBody, and can be considered as combination of @Controller and @ResponseBody.
 @RequestBody : If a method parameter is annotated with @RequestBody, Spring will bind the incoming HTTP request body(for the URL mentioned in @RequestMapping for that method) to that parameter. While doing that, Spring will [behind the scenes] use HTTP Message converters to convert the HTTP request body into domain object [deserialize request body to domain object], based on ACCEPT or Content-Type header present in request.
 @ResponseBody : If a method is annotated with @ResponseBody, Spring will bind the return value to outgoing HTTP response body. While doing that, Spring will [behind the scenes] use HTTP Message converters to convert the return value to HTTP response body [serialize the object to response body], based on Content-Type present in request HTTP header. As already mentioned, in Spring 4, you may stop using this annotation.
 ResponseEntity is a real deal. It represents the entire HTTP response. Good thing about it is that you can control anything that goes into it. You can specify status code, headers, and body. It comes with several constructors to carry the information you want to sent in HTTP Response.
 @PathVariable This annotation indicates that a method parameter should be bound to a URI template variable [the one in '{}'].
 Basically, @RestController , @RequestBody, ResponseEntity & @PathVariable are all you need to know to implement a REST API in Spring. Additionally, spring provides several support classes to help you implement something customized.
 MediaType : Although we didn’t, with @RequestMapping annotation, you can additionally, specify the MediaType to be produced or consumed (using produces or consumes attributes) by that particular controller method, to further narrow down the mapping.

*/


@RestController
@RequestMapping(path = "/api")
public class UserController {
	
	/* Logger
	 * 		using the slf4 logging framework.
	 */
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	// ------------------------------------------
	// --------- Dependencies--------------------
	@Autowired
	private UserService userService;
	
	@Autowired
	private OtpService otpService;
	
	
	// ------------------------------------------
	// --------- New User Registration ----------
	// SignUp
	@PostMapping("/signup")
	public ResponseEntity<StandardResponse> register(@RequestBody UserRequestRegister userRequestRegister){
		
		// TODO Implement the validation of mobile number and email address.
		// 		Then send the OTP.
		
		// logging
		logger.info("class=UserController | method=register | request=/signup | userRequest: "+ userRequestRegister.toString());
		
		//Save to database
		boolean isSaved = userService.save(userRequestRegister);
		
		//Generate OTP and store into local cache.
		otpService.generateAndSendtoEmailAndSendtoMobile(userRequestRegister);
			
		//set the response object
		ResponseEntity<StandardResponse> response;
		if(isSaved) {
			StandardResponse standardResponse = new StandardResponse("otp was sent to email and phone. Please enter otp.");
			response = ResponseEntity.ok(standardResponse);
		}
		else {
			StandardResponse standardResponse = new StandardResponse("You are existing user. Otp send to mobile and email. please enter it. Or failed to save user.");
			response = ResponseEntity.ok(standardResponse);
		}
		
		return response;
	}
	
	
	//---------- Existing User Login  ------------
	// Using email Address to Login
	@PostMapping("/signin/useremail")
	public ResponseEntity<StandardResponse> loginWithEmail(@RequestBody LoginEmail loginEmail){
		// logging
		logger.info("class=UserController | method=loginWithEmail | request=/signin/useremail | loginEmail: "+ loginEmail.toString());

		String message = userService.validateAndSendOtpToEmail(loginEmail);
		return ResponseEntity.ok(new StandardResponse(message));
	}
	
	@PostMapping("/signin/usertelephonenumber")
	public ResponseEntity<StandardResponse> loginWithTelephoneNumber(@RequestBody LoginTelephoneNumber loginTelephoneNumber ){
		// logging
		logger.info("class=UserController | method=loginWithTelephoneNumber | request=/signin/usertelephonenumber | loginTelephoneNumber: "+ loginTelephoneNumber.toString());
		
		String message = userService.validateAndSendOtpToTelephoneNumber(loginTelephoneNumber);
		return ResponseEntity.ok(new StandardResponse(message));
	}
	
	
	// ---------- Sign In Verification -------------
	// SignIn verification with Email 
	@PostMapping("/authentication/useremail")
	public ResponseEntity AuthenticateWithEmail(@RequestBody UserLoginEmail userLoginEmail){
		// logging
		logger.info("class=UserController | method=AuthenticateWithEmail | request=/authentication/useremail | userLoginEmail: "+ userLoginEmail.toString());
		
		boolean isValidOtp = false;
		String returnMessage = "";
		
		if(null != userLoginEmail) {
//			// Get the mobile number related to email
//			if(null != userLoginEmail.getUserEmail()) {
//				userTelephoneNumber = userService.getTelephoneNumberFromEmail(userLoginEmail.getUserEmail());
//			} else {
//				returnMessage = "email Address is not found";
//			}
			//check for user OTP validation
			if(null != userLoginEmail.getUserEmail() && null != userLoginEmail.getOtp()) {
				isValidOtp = otpService.isOtpValid(userLoginEmail.getUserEmail(), userLoginEmail.getOtp());
			} else {
				returnMessage = "OTP is not found.";
			}
		}
		
		//Generate the JWT token.
		if(isValidOtp == true) {
			returnMessage = userService.createToken(userService.getTelephoneNumberFromEmail(userLoginEmail.getUserEmail()));
			return ResponseEntity.ok(new SuccessToken(returnMessage));
			
		} else if (returnMessage == "") {
			returnMessage = "OTP is expired or it is wrong.";
		}
		
		return ResponseEntity.badRequest().body(returnMessage);
		
	}
	
	// SignIn with telephone number
	@PostMapping("/authentication/usertelephonenumber")
	public ResponseEntity AuthenticateWithTelephoneNumber(@RequestBody UserLoginTelephoneNumber loginTelephoneNumber){
		// logging
		logger.info("class=UserController | method=AuthenticateWithTelephoneNumber | request=/authentication/usertelephonenumber | loginTelephoneNumber: "+ loginTelephoneNumber.toString());
		
		String userTelephoneNumber = "";
		boolean isValidOtp = false;
		String returnMessage = "";
		
		if(null != loginTelephoneNumber) {
			// Get the mobile number
			if(null != loginTelephoneNumber.getTelephoneNumber()) {
				userTelephoneNumber = loginTelephoneNumber.getTelephoneNumber();
			} else {
				returnMessage = "Telephone Number is not found.";
			}
			//check for user OTP validation
			if(null != loginTelephoneNumber.getOtp()) {
				isValidOtp = otpService.isOtpValid(userTelephoneNumber, loginTelephoneNumber.getOtp());
			} else {
				returnMessage = "OTP is not found.";
			}
		}
		
		if(isValidOtp == true) {
//			returnMessage = jwtTokenProvider.createTokenWithTelephoneNumberAndRole(new HashMap<>(), userTelephoneNumber, "USER");
			returnMessage = userService.createToken(userTelephoneNumber);
			return ResponseEntity.ok(new SuccessToken(returnMessage));
			
		} else if (returnMessage == "") {
			returnMessage = "OTP is expired or it is wrong.";
		}
		
		return ResponseEntity.badRequest().body(returnMessage);
	}
	
	
	// ---------- Sign In Verification - All in one. -------------
	// SignIn verification with Email 
	@PostMapping("/authentication")
	public ResponseEntity AuthenticateUser(@RequestBody UserLogin userLogin){
		// logging
		logger.info("class=UserController | method=AuthenticateUser | request=/authentication | userLogin: "+ userLogin.toString());		
		
		boolean isValidOtp = false;
		boolean isTelephoneNumberMatch = true;
		String returnMessage = "";
		String userTelephoneNumber = "";	
			
		if(null != userLogin) {
			// Get the mobile number related to email
			if(null != userLogin.getUserEmail() && null == userLogin.getTelephoneNumber()) {
				userTelephoneNumber = userService.getTelephoneNumberFromEmail(userLogin.getUserEmail());
			} else if(null == userLogin.getUserEmail() && null != userLogin.getTelephoneNumber()) {
				userTelephoneNumber = userLogin.getTelephoneNumber();
			} else if(null != userLogin.getUserEmail() && null != userLogin.getTelephoneNumber()) {
				userTelephoneNumber = userService.getTelephoneNumberFromEmail(userLogin.getUserEmail());
				if(userTelephoneNumber.equals(userLogin.getTelephoneNumber())) {
					isTelephoneNumberMatch = true;
				} else {
					isTelephoneNumberMatch = false;
					returnMessage = "email and telephone number do not match.";
				}
			} else {
				returnMessage = "Please provide an email address or telephone number.";
			}
					
			if(null != userLogin.getOtp() && isTelephoneNumberMatch == true) {
				isValidOtp = otpService.isOtpValid(userTelephoneNumber, userLogin.getOtp());
			} else if(null == userLogin.getOtp()){
				returnMessage = "OTP was not found.";
			}
				
		} else {
			returnMessage = "provide a request body in the right format.";
		}
			
		//Generate the JWT token.
		if(isValidOtp == true) {
			returnMessage = userService.createToken(userTelephoneNumber);
			return ResponseEntity.ok(new SuccessToken(returnMessage));
		} else if (returnMessage == "") {
			returnMessage = "OTP is expired or it is wrong.";
		}
			
		return ResponseEntity.badRequest().body(returnMessage);
			
	}
		
			
	
	// ********* User CRUD Operation **********
	@DeleteMapping("/deleteuser/{telephonenumber}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String delete(@PathVariable String telephoneNumber) {
		// logging
		logger.info("class=UserController | method=delete | request=/deleteuser/{telephonenumber | telephoneNumber: "+ telephoneNumber.toString());		
		
		userService.delete(telephoneNumber);
		return "deleted Sucessfull :"+telephoneNumber;
	}

	
	// TODO - Search User.
	
	
	// ************* Refresh the token ***********
	@GetMapping("/refresh")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
	public String refresh(HttpServletRequest req) {
		// logging
		logger.info("class=UserController | method=refresh | request=/refresh | req: "+ req.toString());		
				
		return userService.refresh(req.getRemoteUser());
	}
	
	
	// For testing of token usage.
	@GetMapping("/me")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public User whoami(HttpServletRequest req) {
		// logging
		logger.info("class=UserController | method=whoami | request=/me | req: "+ req.toString());				
		
		return userService.whoami(req);
	}
	

}

package com.transport.controller;


import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.transport.payloads.LoginTelephoneNumber;
import com.transport.payloads.StandardResponse;
import com.transport.payloads.UserLoginEmail;
import com.transport.payloads.UserRequestRegister;
import com.transport.services.OtpService;
import com.transport.services.UserService;
import com.transport.util.JwtUtil;


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
	
	@Autowired
	private JwtUtil jwtUtil;
	
	
	// ------------------------------------------
	// --------- Login Controller Mappings -------
	@PostMapping("/register")
	public ResponseEntity<StandardResponse> register(@RequestBody UserRequestRegister userRequestRegister){
		
		//Save to database
		boolean isSaved = userService.save(userRequestRegister);
		
		//Generate OTP and store into local cache.
		otpService.generateAndSendtoEmailAndSendtoMobile(userRequestRegister);
			
		//set the response object
		ResponseEntity<StandardResponse> response;
		if(isSaved) {
			StandardResponse standardResponse = new StandardResponse("otp was send to email and phone. please enter otp.");
			response = ResponseEntity.ok(standardResponse);
		}
		else {
			StandardResponse standardResponse = new StandardResponse("failed to save user");
			response = ResponseEntity.ok(standardResponse);
		}
		
		return response;
	}
	
	
	@PostMapping("/authentication/useremail")
	public ResponseEntity<StandardResponse> loginWithEmail(@RequestBody UserLoginEmail userLoginEmail){
		String userTelephoneNumber = "";
		boolean isValidOtp = false;
		String returnMessage = "";
		
		if(null != userLoginEmail) {
			// Get the mobile number related to email
			if(null != userLoginEmail.getUserEmail()) {
				userTelephoneNumber = userService.getTelephoneNumberFromEmail(userLoginEmail.getUserEmail());
			} else {
				returnMessage = "email Address is not found";
			}
			//check for user OTP validation
			if(null != userLoginEmail.getOtp()) {
				isValidOtp = otpService.isOtpValid(userTelephoneNumber, userLoginEmail.getOtp());
			} else {
				returnMessage = "OTP is not found.";
			}
		}
		
		//Generate the JWT token.
		if(isValidOtp == true) {
			returnMessage = jwtUtil.createTokenWithTelephoneNumberAndRole(new HashMap<>(), userTelephoneNumber, "USER");
		} else if (returnMessage == "") {
			returnMessage = "OTP is expired or it is wrong.";
		}
		
		return ResponseEntity.ok(new StandardResponse(returnMessage));
		
	}
	
	
	@PostMapping("/authentication/userTelephoneNumber")
	public ResponseEntity<StandardResponse> loginWithTelephoneNumber(@RequestBody LoginTelephoneNumber loginTelephoneNumber){
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
			returnMessage = jwtUtil.createTokenWithTelephoneNumberAndRole(new HashMap<>(), userTelephoneNumber, "USER");
		} else if (returnMessage == "") {
			returnMessage = "OTP is expired or it is wrong.";
		}
		
		return ResponseEntity.ok(new StandardResponse(returnMessage));
		
	}
	
	
	

	
	
	
	
	
	

}

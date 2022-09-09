package com.transport.util.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

@Service
public class TwilioSmsSender {

	private static final Logger logger = LoggerFactory.getLogger(TwilioSmsSender.class);
	
	public void sendsms(String telephoneNumber, Integer otp) {
		PhoneNumber to = new PhoneNumber("+919408234854");
		PhoneNumber from = new PhoneNumber("+18559585109");
		String otpMessage = "Dear Customer, Your OTP is ##" + otp + "##";
		MessageCreator creator = Message.creator(to, from, otpMessage);
		creator.create();
		logger.info("SMS send to the relevant phone number {}", telephoneNumber);	
	}

}

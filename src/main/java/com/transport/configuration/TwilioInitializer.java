package com.transport.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.twilio.Twilio;

@Configuration
public class TwilioInitializer {
	
//	@Value("${twilio.account_sid}")
//	private String accountSid;
//	
//	@Value("${twilio.auth_token}")
//	private String authToken;
//	
	
	
	@Autowired
	public TwilioInitializer(TwilioConfigurations twilioConfiguration ) {
		Twilio.init(twilioConfiguration.getAccount_sid(), twilioConfiguration.getAuth_token());
	}
}

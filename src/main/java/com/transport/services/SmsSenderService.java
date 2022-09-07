package com.transport.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transport.util.sms.TwilioSmsSender;

@Service
public class SmsSenderService {
	
	@Autowired
	TwilioSmsSender twilioSmsSender;
	
	public void sendSmsByService(String telephoneNumber, Integer otp) {
		twilioSmsSender.sendsms(telephoneNumber, otp);
	}
	
}

package com.transport.services;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.transport.payloads.UserRequestRegister;

@Service
public class OtpService {

	private static final Integer EXPIRE_MINS = 1;
	private LoadingCache<String, Integer> otpCache;

	@Autowired
	private EmailService emailService; 
	
	
	public OtpService() {
		super();
		otpCache = CacheBuilder.newBuilder()
				.expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES)
				.build(new CacheLoader<String, Integer>() {
					@Override
					public Integer load(String key) throws Exception {
						return 0;
					}
				});
		
	}
	
	private int generateOtp(String key) {
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		otpCache.put(key, otp);
		return otp;
	}
	
	
	public void generateAndSendtoEmailAndSendtoMobile(UserRequestRegister userRequestRegister) {
		//generate otp
		int otp = this.generateOtp(userRequestRegister.getTelephoneNumber());
		
		//send to email
		String to = userRequestRegister.getUserEmail();
		String subject = "Verification OTP code";
		String message = "Verification OTP code is "+otp;
		emailService.sendOtpMessage(to, subject, message);
		
		//send to mobile
		
		
	}

}

package com.transport.services;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.transport.payloads.UserLoginEmail;
import com.transport.payloads.UserRequestRegister;

@Service
public class OtpService {

	private static final Integer EXPIRE_MINS = 10;
	private LoadingCache<String, Integer> otpCache;

	@Autowired
	private EmailService emailService; 
	
	@Autowired
	private SmsSenderService smsSenderService;
	
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
	
	public int generateOtp(String key) {
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		otpCache.put(key, otp);
		return otp;
	}
	
	
	public void generateAndSendtoEmail(String emailAddress) {
		// generate otp.
		int otp = this.generateOtp(emailAddress);
		
		//send to email
		String to = emailAddress;
		String subject = "Verification OTP code";
		String message = "Verification OTP code is "+otp;
		try {
			emailService.sendOtpMessage(to, subject, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void generateAndSendtoTelephoneNumber(String telephoneNumber) {
		//generate otp
		int otp = this.generateOtp(telephoneNumber);
				
		//send to mobile
		try {
			smsSenderService.sendSmsByService(telephoneNumber, otp);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	public void generateAndSendtoEmailAndSendtoMobile(UserRequestRegister userRequestRegister) {
		//generate otp
		int otp = this.generateOtp(userRequestRegister.getTelephoneNumber());
		
		//send to email
		String to = userRequestRegister.getUserEmail();
		String subject = "Verification OTP code";
		String message = "Verification OTP code is "+otp;
		try {
			emailService.sendOtpMessage(to, subject, message);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//send to mobile
		try {
			smsSenderService.sendSmsByService(userRequestRegister.getTelephoneNumber(), otp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void clearOtp(String key) {
		otpCache.invalidate(key);
	}

	
	public boolean isOtpValid(String userTelephoneNumber, Integer userOtp) {
		boolean successFlag = false;
			
		Integer systemOtp;
		try {
			systemOtp = otpCache.get(userTelephoneNumber);
		} catch (Exception e) {
			systemOtp = 0;
		}
			
		if(userOtp.intValue() == systemOtp.intValue()) {
			successFlag = true;
			this.clearOtp(userTelephoneNumber);
		}
			
		return successFlag;
	}	
	
	
}

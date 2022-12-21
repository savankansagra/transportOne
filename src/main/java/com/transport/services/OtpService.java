package com.transport.services;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.transport.controller.DriverController;
import com.transport.payloads.UserLoginEmail;
import com.transport.payloads.UserRequestRegister;

@Service
public class OtpService {

	private static final Integer EXPIRE_MINS = 10;
	private LoadingCache<String, Integer> otpCache;
	Logger logger = LoggerFactory.getLogger(OtpService.class);
	
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
		logger.info("method=generateOtp | generated otp is:" + otp );
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
		
		// make async call to the sending email function.
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Callable<Void> task = new Callable<Void>() {

			@Override
			public Void call() throws Exception {
				//send to email
				String to = userRequestRegister.getUserEmail();
				String subject = "Verification OTP code";
				String message = "Verification OTP code is "+otp;
				try {
					emailService.sendOtpMessage(to, subject, message);
				} catch (Exception e) {
					// TODO: handle exception
				}
				return null;
			}
			
		};
		executor.submit(task);
		
		
		// TODO : check authentication of sending OTP to mobile.
		/*
		//send to mobile		
		try {
			smsSenderService.sendSmsByService(userRequestRegister.getTelephoneNumber(), otp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
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

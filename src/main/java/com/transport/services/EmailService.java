package com.transport.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendOtpMessage(String to, String subject, String message){
		MimeMessage msg = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(msg, true);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(message);
			
			javaMailSender.send(msg);
		} catch (MessagingException e) {
			e.printStackTrace();
		}	
	}
}

package com.transport.util;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class ValidationHelper {
	
	public boolean isValidEmail(String emailAddress) {
		final String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
		Pattern pat = Pattern.compile(emailRegex);
        if (emailAddress == null)
            return false;
        return pat.matcher(emailAddress).matches();
	}

	public boolean isValidTelephoneNumber(String telephoneNumber) {
		// Implementing only Indian. But for all country verification you can Using Google libphonenumber API. 
		// TODO Add the Api based validation or something new.
		// final String telephoneNumberRegex = "(0//+91)?[6-9][0-9]{9}";
		final String telephoneNumberRegex = "\\+91[6-9][0-9]{9}";
		
		Pattern pat = Pattern.compile(telephoneNumberRegex);
		if(telephoneNumber == null) {
			return false;
		}
		return pat.matcher(telephoneNumber).matches();
		
	}
	
	
	
	
}

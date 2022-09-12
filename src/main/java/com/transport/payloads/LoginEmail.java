package com.transport.payloads;

public class LoginEmail {
	String emailAddress;

	public LoginEmail() {
		super();
	}
	
	public LoginEmail(String emailAddress) {
		super();
		this.emailAddress = emailAddress;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}


	@Override
	public String toString() {
		return "LoginEmail [emailAddress=" + emailAddress + "]";
	}
	
	
	
}

package com.transport.payloads;

public class LoginEmail {
	String emailAddress;

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public LoginEmail(String emailAddress) {
		super();
		this.emailAddress = emailAddress;
	}

	public LoginEmail() {
		super();
	}

	@Override
	public String toString() {
		return "LoginEmail [emailAddress=" + emailAddress + "]";
	}
	
	
	
}

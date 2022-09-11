package com.transport.payloads;

public class LoginTelephoneNumber {
	private String telephoneNumber;

	public LoginTelephoneNumber(String telephoneNumber) {
		super();
		this.telephoneNumber = telephoneNumber;
	}

	public LoginTelephoneNumber() {
		super();
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	@Override
	public String toString() {
		return "LoginTelephoneNumber [telephoneNumber=" + telephoneNumber + "]";
	}
	
	
}

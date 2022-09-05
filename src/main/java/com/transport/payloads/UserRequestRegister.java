package com.transport.payloads;

public class UserRequestRegister {
	
	private String userEmail;
	private String telephoneNumber;
	
	public UserRequestRegister(String userEmail, String telephoneNumber) {
		super();
		this.userEmail = userEmail;
		this.telephoneNumber = telephoneNumber;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	@Override
	public String toString() {
		return "UserRequestRegister [userEmail=" + userEmail + ", telephoneNumber=" + telephoneNumber + "]";
	}
	
	
}

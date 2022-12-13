package com.transport.payloads;

public class UserLogin {
	
	private String telephoneNumber;
	private String userEmail;
	private Integer Otp;
	
	/** constructors. */
	public UserLogin(String telephoneNumber, String userEmail, Integer otp) {
		super();
		this.telephoneNumber = telephoneNumber;
		this.userEmail = userEmail;
		Otp = otp;
	}
	public UserLogin() {
		super();
	}


	/** getters and setters. */
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public Integer getOtp() {
		return Otp;
	}
	public void setOtp(Integer otp) {
		Otp = otp;
	}

	@Override
	public String toString() {
		return "UserLogin [telephoneNumber=" + telephoneNumber + ", userEmail=" + userEmail + ", Otp=" + Otp + "]";
	}
}

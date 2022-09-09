package com.transport.payloads;

public class UserLoginEmail {
	private String userEmail;
	private Integer Otp;
	
	public UserLoginEmail(String userEmail, Integer otp) {
		super();
		this.userEmail = userEmail;
		Otp = otp;
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
		return "UserLoginEmail [userEmail=" + userEmail + ", Otp=" + Otp + "]";
	}
	
	
}

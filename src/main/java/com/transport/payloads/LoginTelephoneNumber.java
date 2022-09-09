package com.transport.payloads;

public class LoginTelephoneNumber {
	private String telephoneNumber;
	private Integer otp;
	
	public LoginTelephoneNumber(String telephoneNumber, Integer otp) {
		super();
		this.telephoneNumber = telephoneNumber;
		this.otp = otp;
	}
	
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public Integer getOtp() {
		return otp;
	}
	public void setOtp(Integer otp) {
		this.otp = otp;
	}
	
	@Override
	public String toString() {
		return "UserLoginTelephoneNumber [telephoneNumber=" + telephoneNumber + ", otp=" + otp + "]";
	}
	
	
}

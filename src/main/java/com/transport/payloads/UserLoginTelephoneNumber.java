package com.transport.payloads;

public class UserLoginTelephoneNumber {
	private String telephoneNumber;
	private Integer Otp;
	
	public UserLoginTelephoneNumber(String telephoneNumber, Integer Otp) {
		super();
		this.telephoneNumber = telephoneNumber;
		this.Otp = Otp;
	}
	
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public Integer getOtp() {
		return Otp;
	}
	public void setOtp(Integer Otp) {
		this.Otp = Otp;
	}
	
	@Override
	public String toString() {
		return "UserLoginTelephoneNumber [telephoneNumber=" + telephoneNumber + ", otp=" + Otp + "]";
	}
	
	
}

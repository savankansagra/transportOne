package com.transport.payloads;

import java.util.List;

import com.transport.entities.UserRoles;

public class UserRequestRegister {
	
	private String userEmail;
	private String telephoneNumber;
	private List<UserRoles> userRoles; 
	
	public UserRequestRegister(String userEmail, String telephoneNumber, List<UserRoles> userRoles) {
		super();
		this.userEmail = userEmail;
		this.telephoneNumber = telephoneNumber;
		this.userRoles = userRoles;
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
	public List<UserRoles> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(List<UserRoles> userRoles) {
		this.userRoles = userRoles;
	}
	@Override
	public String toString() {
		return "UserRequestRegister [userEmail=" + userEmail + ", telephoneNumber=" + telephoneNumber + ", userRoles="
				+ userRoles + "]";
	}
}

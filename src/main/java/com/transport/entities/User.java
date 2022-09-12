package com.transport.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "testuser")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	@Column(name = "useremail")
	String userEmail;
	
	@Column(name = "telephonenumber")
	String telephoneNumber;

	@Column
	@ElementCollection(fetch = FetchType.EAGER)
	List<UserRoles> appUserRoles;
	
	
	public User() {
		super();
	}

	public User(String userEmail, String telephoneNumber, List<UserRoles> appUserRoles) {
		super();
		this.userEmail = userEmail;
		this.telephoneNumber = telephoneNumber;
		this.appUserRoles = appUserRoles;
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

	public List<UserRoles> getAppUserRoles() {
		return appUserRoles;
	}

	public void setAppUserRoles(List<UserRoles> appUserRoles) {
		this.appUserRoles = appUserRoles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userEmail=" + userEmail + ", telephoneNumber=" + telephoneNumber
				+ ", appUserRoles=" + appUserRoles + "]";
	}

	
	
	
}

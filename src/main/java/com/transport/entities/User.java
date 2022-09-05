package com.transport.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
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

	
	
	public User() {
		super();
	}

	public User(String userEmail, String telephoneNumber) {
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
		return "User [id=" + id + ", userEmail=" + userEmail + ", telephoneNumber=" + telephoneNumber + "]";
	}
	
}

package com.transport.entities;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "testuser")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "useremail")
	private String userEmail;
	
	@Column(name = "telephonenumber")
	private String telephoneNumber;

	@Column
	@ElementCollection(fetch = FetchType.EAGER)
	private List<UserRoles> appUserRoles;
	
	@OneToMany(mappedBy = "user")
	private Collection<TruckDetails> truckDetails;
	
	
	/** constructors */
	public User() {
		super();
	}

	public User(String userEmail, String telephoneNumber, List<UserRoles> appUserRoles) {
		super();
		this.userEmail = userEmail;
		this.telephoneNumber = telephoneNumber;
		this.appUserRoles = appUserRoles;
	}

	
	/** getters and setters */
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

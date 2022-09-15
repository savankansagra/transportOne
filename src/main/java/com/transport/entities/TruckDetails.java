package com.transport.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "truckdetails")
public class TruckDetails {
	
	@Id
	@Column(name="vehicalnumber")
	String vehicalNumber;
	
	
	@Column(name="trucktype")
	String truckType;
	
	@Column(name="trucklength")
	String truckLength;
	
	@Column(name="truckcompany")
	String truckCompany;
	
	@Column(name="truckcapacity")
	String truckCapacity;
	
	//make reference to the User table.
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	private User user;

	public TruckDetails() {
		super();
	}

	public TruckDetails(String vehicalNumber, String truckType, String truckLength, String truckCompany,
			String truckCapacity, User user) {
		super();
		this.vehicalNumber = vehicalNumber;
		this.truckType = truckType;
		this.truckLength = truckLength;
		this.truckCompany = truckCompany;
		this.truckCapacity = truckCapacity;
		this.user = user;
	}
	
	public String getVehicalNumber() {
		return vehicalNumber;
	}

	public void setVehicalNumber(String vehicalNumber) {
		this.vehicalNumber = vehicalNumber;
	}

	public String getTruckType() {
		return truckType;
	}

	public void setTruckType(String truckType) {
		this.truckType = truckType;
	}

	public String getTruckLength() {
		return truckLength;
	}

	public void setTruckLength(String truckLength) {
		this.truckLength = truckLength;
	}

	public String getTruckCompany() {
		return truckCompany;
	}

	public void setTruckCompany(String truckCompany) {
		this.truckCompany = truckCompany;
	}

	public String getTruckCapacity() {
		return truckCapacity;
	}

	public void setTruckCapacity(String truckCapacity) {
		this.truckCapacity = truckCapacity;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "TruckDetails [vehicalNumber=" + vehicalNumber + ", truckType=" + truckType + ", truckLength="
				+ truckLength + ", truckCompany=" + truckCompany + ", truckCapacity=" + truckCapacity + ", user=" + user
				+ "]";
	}	
	
}

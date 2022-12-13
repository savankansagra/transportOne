package com.transport.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.transport.payloads.NewReturnTruckDetail;

@Entity
@Table(name = "return_truck_details")
public class ReturnTruckDetails {
	
	@Id
	@Column(name="vehicle_number")
	private String truckNumber;
	
	@Column(name="return_from")
	private String returnFrom;
	
	@Column(name="return_to")
	private String returnTo;
	
	@Column(name="total_distance")
	private Integer totalDistance;
	
	@Column(name="carring_capacity_ton")
	private Integer carringCapacityInTon;
	
	@Column(name="length_feet")
	private Integer lengthInFeet;
	
	@Column(name="truck_type")
	private String truckType;
	
	@Column(name="available_from")
	private Date availableFrom;
	
	@Column(name="available_to")
	private Date availableTo;
	
	@Column(name="minumum_price")
	private Integer minimumPrice;
	
	@Column(name="maximum_price")
	private Integer maximumPrice;
	
	@Column(name="current_status")
	private String currentStatus;

	/** references */
	//make reference to the User table.
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	private User user;
	
	/** constructors */
	public ReturnTruckDetails() {
		super();
	}

	
	public ReturnTruckDetails(NewReturnTruckDetail newReturnTruckDetail, User user) {
		super();
		this.truckNumber = newReturnTruckDetail.getTruckNumber();
		this.returnFrom = newReturnTruckDetail.getReturnFrom();
		this.returnTo = newReturnTruckDetail.getReturnTo();
		this.totalDistance = newReturnTruckDetail.getTotalDistance();
		this.carringCapacityInTon = newReturnTruckDetail.getCarringCapacityInTon();
		this.lengthInFeet = newReturnTruckDetail.getLengthInFeet();
		this.truckType = newReturnTruckDetail.getTruckType();
		this.availableFrom = newReturnTruckDetail.getAvailableFrom();
		this.availableTo = newReturnTruckDetail.getAvailableTo();
		this.minimumPrice = newReturnTruckDetail.getMinimumPrice();
		this.maximumPrice = newReturnTruckDetail.getMaximumPrice();
		this.currentStatus = newReturnTruckDetail.getCurrentStatus();
		this.user = user;
	}
	
	
	
	/** Update the object using the vehicle number as primary key */
	public ReturnTruckDetails updateReturnTruckDetails(NewReturnTruckDetail newReturnTruckDetail) {
		// do not update primary key.
		//this.truckNumber = newReturnTruckDetail.getTruckNumber();
		// do not update user as well.
		this.returnFrom = newReturnTruckDetail.getReturnFrom();
		this.returnTo = newReturnTruckDetail.getReturnTo();
		this.totalDistance = newReturnTruckDetail.getTotalDistance();
		this.carringCapacityInTon = newReturnTruckDetail.getCarringCapacityInTon();
		this.lengthInFeet = newReturnTruckDetail.getLengthInFeet();
		this.truckType = newReturnTruckDetail.getTruckType();
		this.availableFrom = newReturnTruckDetail.getAvailableFrom();
		this.availableTo = newReturnTruckDetail.getAvailableTo();
		this.minimumPrice = newReturnTruckDetail.getMinimumPrice();
		this.maximumPrice = newReturnTruckDetail.getMaximumPrice();
		this.currentStatus = newReturnTruckDetail.getCurrentStatus();
		
		return this;
	}


	/** Getter method */


	public String getTruckNumber() {
		return truckNumber;
	}


	public String getReturnFrom() {
		return returnFrom;
	}


	public String getReturnTo() {
		return returnTo;
	}


	public Integer getTotalDistance() {
		return totalDistance;
	}


	public Integer getCarringCapacityInTon() {
		return carringCapacityInTon;
	}


	public Integer getLengthInFeet() {
		return lengthInFeet;
	}


	public String getTruckType() {
		return truckType;
	}


	public Date getAvailableFrom() {
		return availableFrom;
	}


	public Date getAvailableTo() {
		return availableTo;
	}


	public Integer getMinimumPrice() {
		return minimumPrice;
	}


	public Integer getMaximumPrice() {
		return maximumPrice;
	}


	public String getCurrentStatus() {
		return currentStatus;
	}


	public User getUser() {
		return user;
	}
	
	
	
	/** printing the object for reference for developer only. */
	@Override
	public String toString() {
		return "ReturnTruckDetails [truckNumber=" + truckNumber + ", returnFrom=" + returnFrom + ", returnTo="
				+ returnTo + ", totalDistance=" + totalDistance + ", carringCapacityInTon=" + carringCapacityInTon
				+ ", lengthInFeet=" + lengthInFeet + ", truckType=" + truckType + ", availableFrom=" + availableFrom
				+ ", availableTo=" + availableTo + ", minimumPrice=" + minimumPrice + ", maximumPrice=" + maximumPrice
				+ ", currentStatus=" + currentStatus + ", user=" + user + "]";
	}

	
}

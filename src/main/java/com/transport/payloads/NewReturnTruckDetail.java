package com.transport.payloads;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class NewReturnTruckDetail {
	/** origin and destination details. */
	private String returnFrom;
	private String returnTo;
	private Integer totalDistance;

	/** truck details */
	private String truckNumber;
	private Integer carringCapacityInTon;
	private Integer lengthInFeet;
	private String truckType;
	
	/** available date and time */
	@DateTimeFormat(pattern = "dd-MM-yyyy hh:mm:ss a")
	@JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss a")   // example:  "31/12/2019 23:59 PM"
	private Date availableFrom;
	@DateTimeFormat(pattern = "dd-MM-yyyy hh:mm:ss a")
	@JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss a")
	private Date availableTo;
	
	/** minimum and maximum price expectation. */
	private Integer minimumPrice;
	private Integer maximumPrice;
	private String currentStatus;
	
	/** constructors */
	public NewReturnTruckDetail() {
		super();
	}
	
	/** getters */
	public String getReturnFrom() {
		return returnFrom;
	}

	public String getReturnTo() {
		return returnTo;
	}

	public Integer getTotalDistance() {
		return totalDistance;
	}

	public String getTruckNumber() {
		return truckNumber;
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


	/** priting obejct as format. */
	@Override
	public String toString() {
		return "NewReturnTruckDetail [returnFrom=" + returnFrom + ", returnTo=" + returnTo + ", totalDistance="
				+ totalDistance + ", truckNumber=" + truckNumber + ", carringCapacityInTon=" + carringCapacityInTon
				+ ", lengthInFeet=" + lengthInFeet + ", truckType=" + truckType + ", availableFrom=" + availableFrom
				+ ", availableTo=" + availableTo + ", minimumPrice=" + minimumPrice + ", maximumPrice=" + maximumPrice
				+ ", currentStatus=" + currentStatus + "]";
	}

	
}

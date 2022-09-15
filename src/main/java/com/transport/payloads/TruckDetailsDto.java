package com.transport.payloads;

public class TruckDetailsDto {
	private String truckType;
	private String truckLength;
	private String truckCompany;
	private String truckCapacity;
	private String vehicalNumber;
	
	public TruckDetailsDto(String truckType, String truckLength, String truckCompany, String truckCapacity,
			String vehicalNumber) {
		super();
		this.truckType = truckType;
		this.truckLength = truckLength;
		this.truckCompany = truckCompany;
		this.truckCapacity = truckCapacity;
		this.vehicalNumber = vehicalNumber;
	}

	public TruckDetailsDto() {
		super();
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

	public String getVehicalNumber() {
		return vehicalNumber;
	}

	public void setVehicalNumber(String vehicalNumber) {
		this.vehicalNumber = vehicalNumber;
	}

	@Override
	public String toString() {
		return "TruckDetailsDto [truckType=" + truckType + ", truckLength=" + truckLength + ", truckCompany="
				+ truckCompany + ", TruckCapacity=" + truckCapacity + ", VehicalNumber=" + vehicalNumber + "]";
	}
	
	
	
}

//package com.transport.entities;
//
//import java.util.Arrays;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.Lob;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "truckdocuments")
//public class TruckDocuments {
//
////	@Id
////	@OneToOne(cascade = CascadeType.ALL)
////	@JoinColumn(name="vehicalnumber")
////	private TruckDetails truckDetails;
////	
//	@Column(name = "rcbookname")
//	private String rcbookName;
//	
//	@Column(name = "rcbookfiletype")
//	private String rcbookFileType;
//	
//	@Lob
//	@Column(name = "rcbookdata")
//	private byte[] rcbookData;
//
//	public TruckDocuments(TruckDetails truckDetails, String rcbookName, String rcbookFileType, byte[] rcbookData) {
//		super();
//		this.truckDetails = truckDetails;
//		this.rcbookName = rcbookName;
//		this.rcbookFileType = rcbookFileType;
//		this.rcbookData = rcbookData;
//	}
//
//	public TruckDocuments() {
//		super();
//	}
//
//	public TruckDetails getTruckDetails() {
//		return truckDetails;
//	}
//
//	public void setTruckDetails(TruckDetails truckDetails) {
//		this.truckDetails = truckDetails;
//	}
//
//	public String getRcbookName() {
//		return rcbookName;
//	}
//
//	public void setRcbookName(String rcbookName) {
//		this.rcbookName = rcbookName;
//	}
//
//	public String getRcbookFileType() {
//		return rcbookFileType;
//	}
//
//	public void setRcbookFileType(String rcbookFileType) {
//		this.rcbookFileType = rcbookFileType;
//	}
//
//	public byte[] getRcbookData() {
//		return rcbookData;
//	}
//
//	public void setRcbookData(byte[] rcbookData) {
//		this.rcbookData = rcbookData;
//	}
//
//	@Override
//	public String toString() {
//		return "TruckDocuments [truckDetails=" + truckDetails + ", rcbookName=" + rcbookName + ", rcbookFileType="
//				+ rcbookFileType + ", rcbookData=" + Arrays.toString(rcbookData) + "]";
//	}
//
//}

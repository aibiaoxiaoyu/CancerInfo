package com.graduate.infocollect.entity;

public class MedicalData {
	private String contactId;
	private String PSA;
	private String CA;
	private String AFP;
	private long createdTime;
	
	public MedicalData(String string) {
		// TODO Auto-generated constructor stub
		contactId = string;
	}
	
	public String getContactId() {
		return contactId;
	}
	
	public String getPSA() {
		return PSA;
	}
	
	public void setPSA(String pSA) {
		PSA = pSA;
	}
	
	public String getCA() {
		return CA;
	}
	
	public void setCA(String cA) {
		CA = cA;
	}
	
	public String getAFP() {
		return AFP;
	}
	
	public void setAFP(String aFP) {
		AFP = aFP;
	}
	
	public long getCreatedTime() {
		return createdTime;
	}
	
	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}
	
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	
}

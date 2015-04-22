package com.graduate.infocollect.entity;

public class MedicalData {
	private String contactId;
	private String CT;
	private String PSA;
	private long createdTime;
	
	public String getContactId() {
		return contactId;
	}
	
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	
	public String getCT() {
		return CT;
	}
	
	public void setCT(String cT) {
		CT = cT;
	}
	
	public String getPSA() {
		return PSA;
	}
	
	public void setPSA(String pSA) {
		PSA = pSA;
	}
	
	public long getCreatedTime() {
		return createdTime;
	}
	
	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}
	
}

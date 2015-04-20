package com.graduate.infocollect.entity;

public class NotifyEntity {
	private String userID;
	private String visitTime;
	
	public NotifyEntity(String userID, String visitTime) {
		super();
		this.userID = userID;
		this.visitTime = visitTime;
	}

	public String getUserID() {
		return userID;
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public String getVisitTime() {
		return visitTime;
	}
	
	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}
	
}

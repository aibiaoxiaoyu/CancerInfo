package com.graduate.infocollect.entity;

public class NotifyEntity {
	private String no;
	private String userID;
	private String visitTime;
	
	public NotifyEntity(String no, String userID, String visitTime) {
		super();
		this.no = no;
		this.userID = userID;
		this.visitTime = visitTime;
	}
	
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
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

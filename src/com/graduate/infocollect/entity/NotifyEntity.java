package com.graduate.infocollect.entity;

public class NotifyEntity {
	private String no;
	private String contacID;
	private String visitTime;
	
	public NotifyEntity() {
		super();
	}
	public NotifyEntity(String no, String contacID, String visitTime) {
		super();
		this.no = no;
		this.contacID = contacID;
		this.visitTime = visitTime;
	}
	
	public String getNo() {
		return no;
	}
	
	public void setNo(String no) {
		this.no = no;
	}
	
	public String getContacID() {
		return contacID;
	}
	
	public void setContacID(String contacID) {
		this.contacID = contacID;
	}
	
	public String getVisitTime() {
		return visitTime;
	}
	
	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}
	
}

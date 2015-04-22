package com.graduate.infocollect.entity;

public class Contact {
	
	public static final int SEX_MAN = 0;
	public static final int SEX_WOMAN = 1;
	private String id;
	private String name;
	private int sex;
	private String birthday;
	
	public Contact() {
		super();
	}
	
	public Contact(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getSex() {
		return sex;
	}
	
	public void setSex(int sex) {
		this.sex = sex;
	}
	
	public String getBirthday() {
		return birthday;
	}
	
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
}

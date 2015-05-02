package com.graduate.infocollect.entity;

import java.io.Serializable;

public class Contact implements Serializable {
	
	/**
	 * @名称：serialVersionUID
	 * @类型：long
	 */
	private static final long serialVersionUID = -4972328048899483631L;
	public static final String CONTACT = "contact";
	public static final int SEX_MAN = 1;
	public static final int SEX_WOMAN = 0;
	private String id;
	private String name;
	private int sex;
	private String birthday;
	private boolean smork;
	private boolean drink;
	private boolean ctNormal;
	
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
	
	public boolean isSmork() {
		return smork;
	}

	public void setSmork(boolean smork) {
		this.smork = smork;
	}

	public boolean isDrink() {
		return drink;
	}

	public void setDrink(boolean drink) {
		this.drink = drink;
	}

	public boolean isCtNormal() {
		return ctNormal;
	}

	public void setCtNormal(boolean ctNormal) {
		this.ctNormal = ctNormal;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Contact other = (Contact)obj;
		if(id == null) {
			if(other.id != null)
				return false;
		}
		else
			if(!id.equals(other.id))
				return false;
		return true;
	}
	
}

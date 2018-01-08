package com.ecommerce.services.bean;

import com.ecommerce.services.entity.PhoneNumberEntity;

public class PhoneNumber {
	
	private int id;
	private PhoneNumberType type;
	private String number;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public PhoneNumberType getType() {
		return type;
	}
	public void setType(PhoneNumberType type) {
		this.type = type;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	public void copyProperties(PhoneNumberEntity entity)
	{
		this.id = entity.getId();
		this.number = entity.getNumber();
		this.type = entity.getType();
	}
	
}

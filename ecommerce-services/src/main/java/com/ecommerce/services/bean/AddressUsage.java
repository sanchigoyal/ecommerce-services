package com.ecommerce.services.bean;

import com.ecommerce.services.entity.AddressUsageEntity;

public class AddressUsage {
	
	private int id;
	private AddressUsageType type;
	private boolean usage;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public AddressUsageType getType() {
		return type;
	}
	public void setType(AddressUsageType type) {
		this.type = type;
	}
	public boolean isUsage() {
		return usage;
	}
	public void setUsage(boolean usage) {
		this.usage = usage;
	}
	
	public void copyProperties(AddressUsageEntity entity)
	{
		this.id = entity.getId();
		this.type = entity.getType();
		this.usage = entity.isUsage();
		
	}
}

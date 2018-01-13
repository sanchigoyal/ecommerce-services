package com.ecommerce.services.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ecommerce.services.bean.AddressUsage;
import com.ecommerce.services.bean.AddressUsageType;

@Entity
@Table(name="ADDRESS_USAGE")
public class AddressUsageEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ADDRESS_USAGE_ID")
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ADDRESS_USAGE_TYPE")
	private AddressUsageType type;
	
	@Column(name="ADDRESS_USAGE")
	private boolean usage;
	
	@ManyToOne
	@JoinColumn(name="ADDRESS_ID", referencedColumnName="ADDRESS_ID")
	private AddressEntity address;
	
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
	public AddressEntity getAddress() {
		return address;
	}
	public void setAddress(AddressEntity address) {
		this.address = address;
	}
	
	public void copyProperties(AddressUsage usage)
	{
		this.id = usage.getId();
		this.type = usage.getType();
		this.usage = usage.isUsage();
	}
}

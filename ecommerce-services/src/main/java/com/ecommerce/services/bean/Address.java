package com.ecommerce.services.bean;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ecommerce.services.entity.AddressEntity;

public class Address {
	
	private int id;
	
	@NotNull(message="{customer.address.addressline1.notnull}")
	@Size(min=1, max=256, message="{customer.address.addressline1.size}")
	private String addressLine1;
	
	private String addressLine2;
	
	@NotNull(message="{customer.address.city.notnull}")
	@Size(min=1, max=256, message="{customer.address.city.size}")
	private String city;
	
	@NotNull(message="{customer.address.state.notnull}")
	@Size(min=1, max=256, message="{customer.address.state.size}")
	private String state;
	
	@NotNull(message="{customer.address.country.notnull}")
	@Size(min=1, max=256, message="{customer.address.country.size}")
	private String country;
	
	@NotNull(message="{customer.address.zipcode.notnull}")
	@Size(min=1, max=256, message="{customer.address.zipcode.size}")
	private String zipCode;
	
	private List<AddressUsage> usages = new ArrayList<AddressUsage>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public List<AddressUsage> getUsages() {
		return usages;
	}
	public void setUsages(List<AddressUsage> usages) {
		this.usages = usages;
	}
	
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	public void copyProperties(AddressEntity entity)
	{
		this.id = entity.getId();
		this.addressLine1 = entity.getAddressLine1();
		this.addressLine2 = entity.getAddressLine2();
		this.city = entity.getCity();
		this.state = entity.getState();
		this.country = entity.getState();
		this.zipCode = entity.getZipcode();
		
		entity.getUsages().forEach(item->{
			AddressUsage usage = new AddressUsage();
			usage.copyProperties(item);
			this.usages.add(usage);
		});
		
	}
}

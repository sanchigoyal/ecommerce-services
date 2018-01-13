package com.ecommerce.services.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ecommerce.services.bean.Address;

@Entity
@Table(name="USER_ADDRESS")
public class AddressEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ADDRESS_ID")
	private int id;
	
	@Column(name="ADDRESS_LINE_1")
	private String addressLine1;
	
	@Column(name="ADDRESS_LINE_2")
	private String addressLine2;
	
	@Column(name="CITY_NM")
	private String city;
	
	@Column(name="STATE_NM")
	private String state;
	
	@Column(name="COUNTRY_NM")
	private String country;
	
	@Column(name="ZIPCODE")
	private String zipcode;
	
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private UserEntity user;
	
	@OneToMany(mappedBy="address", cascade=CascadeType.ALL)
	private List<AddressUsageEntity> usages = new ArrayList<AddressUsageEntity>();
	
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
	public List<AddressUsageEntity> getUsages() {
		return usages;
	}
	public void setUsages(List<AddressUsageEntity> usages) {
		this.usages = usages;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	
	public void copyProperties(Address address)
	{
		this.id = address.getId();
		this.addressLine1 = address.getAddressLine1();
		this.addressLine2 = address.getAddressLine2();
		this.city = address.getCity();
		this.state = address.getState();
		this.country = address.getCountry();
		this.zipcode = address.getZipCode();
		
		// TODO provide link to category here instead of service.
		
		this.usages = new ArrayList<AddressUsageEntity>();
		address.getUsages().forEach(item->{
			AddressUsageEntity entity = new AddressUsageEntity();
			entity.copyProperties(item);
			entity.setAddress(this);
			this.usages.add(entity);
		});
	}
}

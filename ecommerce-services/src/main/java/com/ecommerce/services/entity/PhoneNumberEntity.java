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

import com.ecommerce.services.bean.PhoneNumberType;

@Entity
@Table(name="USER_PHONE_NUM")
public class PhoneNumberEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PHONE_NUM_ID")
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="PHONE_TYPE")
	private PhoneNumberType type;
	
	@Column(name="PHONE_NUM")
	private String number;
	
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private UserEntity user;
	
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
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	
	
}

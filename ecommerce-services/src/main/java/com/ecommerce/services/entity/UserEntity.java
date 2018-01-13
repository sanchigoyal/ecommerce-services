package com.ecommerce.services.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="USER")
public class UserEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_ID")
	private int id;
	
	@Column(name="FIRST_NM")
	private String firstName;
	
	@Column(name="LAST_NM")
	private String lastName;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="DOB")
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
	@OneToMany(mappedBy="user")
	private List<AddressEntity> addresses = new ArrayList<AddressEntity>();
	
	@OneToMany(mappedBy="user")
	private List<PhoneNumberEntity> phones = new ArrayList<PhoneNumberEntity>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public List<AddressEntity> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<AddressEntity> addresses) {
		this.addresses = addresses;
	}
	public List<PhoneNumberEntity> getPhones() {
		return phones;
	}
	public void setPhones(List<PhoneNumberEntity> phones) {
		this.phones = phones;
	}
	
	
	
}

package com.ecommerce.services.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ecommerce.services.entity.UserEntity;

public class User {

	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private Date dateOfBirth;
	private List<Address> addresses = new ArrayList<Address>();
	private List<PhoneNumber> phones = new ArrayList<PhoneNumber>();
	private List<UserRole> roles = new ArrayList<UserRole>();
	private List<Link> links = new ArrayList<Link>();
	
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
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	public List<PhoneNumber> getPhones() {
		return phones;
	}
	public void setPhones(List<PhoneNumber> phones) {
		this.phones = phones;
	}
	
	public List<Link> getLinks() {
		return links;
	}
	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
	public List<UserRole> getRoles() {
		return roles;
	}
	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}
	public void copyPropertiesLazyFetch(UserEntity entity)
	{
		this.id = entity.getId();
		this.firstName = entity.getFirstName();
		this.lastName = entity.getLastName();
		this.email = entity.getEmail();
		this.dateOfBirth = entity.getDateOfBirth();
	}
	
	public void copyProperties(UserEntity entity)
	{
		copyPropertiesLazyFetch(entity);
		
		entity.getAddresses().forEach(item->{
			Address address = new Address();
			address.copyProperties(item);
			this.addresses.add(address);
		});
		
		entity.getPhones().forEach(item->{
			PhoneNumber number = new PhoneNumber();
			number.copyProperties(item);
			this.phones.add(number);
		});
		
		entity.getRoles().forEach(item->{
			UserRole role= new UserRole();
			role.setRole(item.getRole());
			this.roles.add(role);
		});
	}
	
	public void addLink(String ref, String href)
	{
		this.links.add(new Link(ref, href));
	}
	
}

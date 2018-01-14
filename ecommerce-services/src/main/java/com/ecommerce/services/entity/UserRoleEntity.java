package com.ecommerce.services.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserRoleEntity {
	
	@Column(name="ROLE")
	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}

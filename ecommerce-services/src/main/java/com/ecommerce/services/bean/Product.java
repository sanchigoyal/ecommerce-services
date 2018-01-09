package com.ecommerce.services.bean;

import com.ecommerce.services.entity.ProductEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Product {
	
	private int id;
	private String name;
	private String description;
	private boolean active;
	
	@JsonIgnore
	private Category category;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public void copyProperties(ProductEntity entity)
	{
		this.id = entity.getId();
		this.name = entity.getName();
		this.description = entity.getDescription();
		this.active = entity.isActive();
		this.category = new Category();
		category.setId(entity.getCategory().getId());
	}
}

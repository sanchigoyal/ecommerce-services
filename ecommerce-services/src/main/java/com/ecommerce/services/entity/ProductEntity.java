package com.ecommerce.services.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ecommerce.services.bean.Product;

@Entity
@Table(name="PRODUCT")
public class ProductEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PRODUCT_ID")
	private int id;
	
	@Column(name="PRODUCT_NM")
	private String name;
	
	@Column(name="PRODUCT_DESC")
	private String description;
	
	@Column(name="ACTIVE")
	private boolean active;
	
	@ManyToOne
	@JoinColumn(name="CATEGORY_ID")
	private CategoryEntity category;

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

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}
	
	public void copyProperties(Product product)
	{
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.active = product.isActive();
		this.category = new CategoryEntity();
		this.category.setId(product.getCategory().getId());
	}
}

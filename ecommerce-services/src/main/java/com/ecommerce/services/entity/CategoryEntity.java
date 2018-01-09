package com.ecommerce.services.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ecommerce.services.bean.Category;

@Entity
@Table(name="PRODUCT_CATEGORY")
public class CategoryEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CATEGORY_ID")
	private int id;
	
	@Column(name="CATEGORY_NM")
	private String name;
	
	@Column(name="CATEGORY_DESC")
	private String description;
	
	@Column(name="ACTIVE")
	private boolean active;
	
	@OneToMany(mappedBy="category")
	private List<ProductEntity> products;
	
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
	public List<ProductEntity> getProducts() {
		return products;
	}
	public void setProducts(List<ProductEntity> products) {
		this.products = products;
	}
	
	public void copyProperties(Category category)
	{
		this.id = category.getId();
		this.name = category.getName();
		this.description = category.getDescription();
		this.products = new ArrayList<ProductEntity>();
		
		category.getProducts().forEach(item->{
			ProductEntity entity = new ProductEntity();
			entity.copyProperties(item);
			this.products.add(entity);
		});
	}
}

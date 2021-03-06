package com.ecommerce.services.bean;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ecommerce.services.entity.CategoryEntity;

public class Category {

	private int id;
	
	@NotNull(message="{category.name.notnull}")
	@Size(min=1, max=256, message="{category.name.size}")
	private String name;
	
	@NotNull(message="{category.description.notnull}")
	@Size(min=1, max=256, message="{category.description.size}")
	private String description;
	
	private boolean active;
	private List<Product> products = new ArrayList<Product>();
	private List<Link> links = new ArrayList<Link>();
	
	public Category(int id)
	{
		this.id = id;
	}
	
	public Category() {}
	
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
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public List<Link> getLinks() {
		return links;
	}
	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
	public void addLink(String rel, String href)
	{
		this.links.add(new Link(rel, href));
	}
	
	public void copyPropertiesLazyFetch(CategoryEntity entity)
	{
		this.id = entity.getId();
		this.name = entity.getName();
		this.description = entity.getDescription();
		this.active = entity.isActive();
	}
	
	public void copyProperties(CategoryEntity entity)
	{
		copyPropertiesLazyFetch(entity);
		
		entity.getProducts().forEach(item->{
			Product product = new Product();
			product.copyProperties(item);
			this.products.add(product);
		});
	}
	
}

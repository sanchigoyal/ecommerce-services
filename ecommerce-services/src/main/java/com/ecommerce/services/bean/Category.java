package com.ecommerce.services.bean;

import java.util.ArrayList;
import java.util.List;

import com.ecommerce.services.entity.CategoryEntity;

public class Category {

	private int id;
	private String name;
	private String description;
	private boolean active;
	private List<Product> products = new ArrayList<Product>();
	private List<Link> links = new ArrayList<Link>();
	
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

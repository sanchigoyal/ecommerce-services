package com.ecommerce.services.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.services.bean.Category;
import com.ecommerce.services.entity.CategoryEntity;
import com.ecommerce.services.repository.CategoryRepository;
import com.ecommerce.services.util.LinkGenerator;

@Service
@Transactional
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	public void addCategory(Category category) {
		CategoryEntity entity = new CategoryEntity();
		entity.copyProperties(category);
		
		categoryRepository.save(entity);
		
	}

	public List<Category> getAllCategory(UriInfo uriInfo, boolean doExpand) {
		List<Category> categories = new ArrayList<Category>();
		
		if(doExpand)
		{
			Iterable<CategoryEntity> entities = categoryRepository.findAll();
			entities.forEach(item->{
				Category category = new Category();
				category.copyProperties(item);
				addLinks(category, uriInfo);
				categories.add(category);
			});
		}
		else
		{
			Iterable<CategoryEntity> entities = categoryRepository.findAll();
			entities.forEach(item->{
				Category category = new Category();
				category.copyPropertiesLazyFetch(item);
				addLinks(category, uriInfo);
				categories.add(category);
			});
		}
		
		return categories;
	}
	
	public Category getCategory(UriInfo uriInfo, boolean doExpand, int categoryId) {
		Category category = null;
		
		if(doExpand)
		{
			CategoryEntity entity = categoryRepository.findOne(categoryId);
			category = new Category();
			category.copyProperties(entity);
			addLinks(category, uriInfo);
		}
		else
		{
			CategoryEntity entity = categoryRepository.findOne(categoryId);
			category = new Category();
			category.copyPropertiesLazyFetch(entity);
			addLinks(category, uriInfo);
		}
		
		return category;
	}

	public void deleteCategory(int categoryId) {
		
		categoryRepository.delete(categoryId);
	}
	
	public void addLinks(Category category, UriInfo uriInfo)
	{
		category.addLink(
				"self", 
				LinkGenerator.getCategoryResourceInstanceURL(uriInfo,
						category.getId()));
		category.addLink(
				"products",
				LinkGenerator.getProductResourceCollectionURL(uriInfo,
						category.getId()));
		
	}

	

}

package com.ecommerce.services.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.services.bean.Product;
import com.ecommerce.services.entity.ProductEntity;
import com.ecommerce.services.exception.RecordNotFoundException;
import com.ecommerce.services.repository.ProductRepository;

@Service
@Transactional
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	public List<Product> getAllProducts(int categoryId, UriInfo uriInfo) {
		List<Product> products = new ArrayList<Product>();
		List<ProductEntity> entities = productRepository.findAllProductsByCategoryId(categoryId);
		entities.forEach(item->{
			Product product = new Product();
			product.copyProperties(item);
			products.add(product);
		});
		
		return products;
	}

	public Product getProduct(int categoryId, int productId, UriInfo uriInfo) {
		
		ProductEntity entity = productRepository.findOne(productId);
		Product product = null;
		
		if(entity != null)
		{
			product = new Product();
			product.copyProperties(entity);
		}
		else
		{
			throw new RecordNotFoundException(productId, "product record not found");
		}
		
		return product;
	}

	public void addProduct(Product product) {
		
		ProductEntity entity = new ProductEntity();
		entity.copyProperties(product);
		productRepository.save(entity);
		
	}

	public void deleteProduct(int productId) {
		
		productRepository.delete(productId);
		
	}

}

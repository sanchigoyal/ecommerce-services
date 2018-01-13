package com.ecommerce.services.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.services.entity.ProductEntity;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Integer>{

	List<ProductEntity> findAllProductsByCategoryId(int categoryId);

}

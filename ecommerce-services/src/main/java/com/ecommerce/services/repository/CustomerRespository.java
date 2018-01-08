package com.ecommerce.services.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.services.entity.CustomerEntity;

@Repository
public interface CustomerRespository extends CrudRepository<CustomerEntity, Integer> {

}

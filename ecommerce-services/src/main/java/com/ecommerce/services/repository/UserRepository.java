package com.ecommerce.services.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.services.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

	public UserEntity findOneUserByEmailAndPassword(String email, String password);
}

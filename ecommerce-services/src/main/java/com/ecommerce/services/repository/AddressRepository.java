package com.ecommerce.services.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.ecommerce.services.entity.AddressEntity;

public interface AddressRepository extends CrudRepository<AddressEntity, Integer> {

	public List<AddressEntity> findAllAddressesByUserId(int customerId);
}

package com.ecommerce.services.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.services.bean.Address;
import com.ecommerce.services.entity.AddressEntity;
import com.ecommerce.services.entity.UserEntity;
import com.ecommerce.services.repository.AddressRepository;

@Service
@Transactional
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	public List<Address> getAllAddresses(int userId)
	{
		List<AddressEntity> entities = addressRepository.findAllAddressesByUserId(userId);
		List<Address> addresses = new ArrayList<Address>();
		entities.forEach(item->{
			Address address = new Address();
			address.copyProperties(item);
			addresses.add(address);
		});
		
		return addresses;
	}

	public void addAddress(int userId, Address address) {
		
		AddressEntity entity = new AddressEntity();
		entity.copyProperties(address);
		entity.setUser(new UserEntity());
		entity.getUser().setId(userId);
		addressRepository.save(entity);
		
		// post save, id field is populated by JPA.
		address.setId(entity.getId());
	}
	
	public void deleteAddress(int addressId)
	{
		addressRepository.delete(addressId);
	}
}

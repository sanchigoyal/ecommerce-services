package com.ecommerce.services.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.services.bean.Address;
import com.ecommerce.services.entity.AddressEntity;
import com.ecommerce.services.entity.CustomerEntity;
import com.ecommerce.services.repository.AddressRepository;

@Service
@Transactional
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	public List<Address> getAllAddresses(int customerId)
	{
		List<AddressEntity> entities = addressRepository.findAllAddressesByCustomerId(customerId);
		List<Address> addresses = new ArrayList<Address>();
		entities.forEach(item->{
			Address address = new Address();
			address.copyProperties(item);
			addresses.add(address);
		});
		
		return addresses;
	}

	public void addAddress(int customerId, Address address) {
		
		AddressEntity entity = new AddressEntity();
		entity.copyProperties(address);
		entity.setCustomer(new CustomerEntity());
		entity.getCustomer().setId(customerId);
		addressRepository.save(entity);
	}
	
	public void deleteAddress(int addressId)
	{
		addressRepository.delete(addressId);
	}
}

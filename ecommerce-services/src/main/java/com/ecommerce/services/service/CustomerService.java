package com.ecommerce.services.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.services.bean.Customer;
import com.ecommerce.services.entity.CustomerEntity;
import com.ecommerce.services.repository.CustomerRespository;
import com.ecommerce.services.util.LinkGenerator;

@Service
@Transactional
public class CustomerService {
	
	@Autowired
	private CustomerRespository customerRespository;

	public Customer getCustomer(int customerId, UriInfo uriInfo, boolean expand) {
	
		CustomerEntity entity = customerRespository.findOne(customerId);
		Customer customer = new Customer();
		
		if(expand)
		{
			customer.copyProperties(entity);
		}
		else
		{
			customer.copyPropertiesLazyFetch(entity);
		}
		
		addLinks(uriInfo, customer);
		return customer;
	}
	
	public List<Customer> getAllCustomers(UriInfo uriInfo, boolean expand)
	{
		Iterable<CustomerEntity> entities = customerRespository.findAll();
		List<Customer> customers = new ArrayList<Customer>();
		
		if(expand)
		{
			entities.forEach(item->{
				Customer customer = new Customer();
				customer.copyProperties(item);
				addLinks(uriInfo, customer);
				customers.add(customer);
			});
		}
		else
		{
			entities.forEach(item->{
				Customer customer = new Customer();
				customer.copyPropertiesLazyFetch(item);
				addLinks(uriInfo, customer);
				customers.add(customer);
			});
		}
		
		return customers;
	}
	
	public void addLinks(UriInfo uriInfo, Customer customer)
	{
		customer.addLink(
				"self",
				LinkGenerator.getCustomerResourceInstanceURL(uriInfo, customer.getId()));
	}
}

package com.ecommerce.services.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;

import com.ecommerce.services.bean.Customer;
import com.ecommerce.services.service.CustomerService;

@Path("/customers")
public class CustomerResource {
	
	private static final String EXPAND_CUSTOMER = "ALL";
	@Autowired
	private CustomerService customerService;
	
	@GET
	@Path("/{customer-id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCustomer(
			@PathParam("costumer-id") int customerId,
			@Context UriInfo uriInfo,
			@QueryParam("expand") String expand) 
	{
		Customer customer = null;
		
		if(expand != null && EXPAND_CUSTOMER.equalsIgnoreCase(expand))
		{
			customer = customerService.getCustomer(customerId, uriInfo, true);
		}
		else
		{
			customer = customerService.getCustomer(customerId, uriInfo, false);
		}
		
		return Response.status(Status.OK)
				.entity(customer)
				.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCustomers(
			@Context UriInfo uriInfo,
			@QueryParam("expand") String expand)
	{
		List<Customer> customers = null;
		if(expand != null && EXPAND_CUSTOMER.equalsIgnoreCase(expand))
		{
			customers = customerService.getAllCustomers(uriInfo, true);
		}
		else
		{
			customers = customerService.getAllCustomers(uriInfo, false);
		}
		
		return Response.status(Status.OK)
				.entity(customers)
				.build();
		
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
}

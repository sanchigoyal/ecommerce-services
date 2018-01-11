package com.ecommerce.services.resource;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
	
	@Autowired
	private CustomerService customerService;
	
	@GET
	@Path("/{customer-id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCustomer(
			@NotNull(message="{customer.id.notnull}")
			@Min(value=0, message="{customer.id.positive}")
			@PathParam("customer-id") int customerId,
			@Context UriInfo uriInfo,
			@QueryParam("expand") String expand) 
	{
		Customer customer = null;
		
		boolean doExpand = "ALL".equalsIgnoreCase(expand) ? true : false;
		
		customer = customerService.getCustomer(customerId, uriInfo, doExpand);
		
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

		boolean doExpand = "ALL".equalsIgnoreCase(expand) ? true : false;
		
		customers = customerService.getAllCustomers(uriInfo, doExpand);
		
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

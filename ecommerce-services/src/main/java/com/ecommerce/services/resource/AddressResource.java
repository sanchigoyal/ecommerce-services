package com.ecommerce.services.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;

import com.ecommerce.services.bean.Address;
import com.ecommerce.services.service.AddressService;


@Path("/customers/{customer-id}/addresses")
public class AddressResource {
	
	@Autowired
	private AddressService addressService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAddresses(
			@PathParam("customer-id") int customerId,
			@Context UriInfo uriInfo)
	{
		return Response.status(Status.OK)
				.entity(addressService.getAllAddresses(customerId))
				.build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addAddress(
			@PathParam("customer-id") int customerId,
			Address address)
	{
		addressService.addAddress(customerId, address);
		System.out.println("Debug>>Sanchi>> customer - "+ customerId + " address line - "+address.getAddressLine1());
		return Response.status(Status.CREATED).build();
	}
	
	@DELETE
	@Path("/{address-id}")
	public Response deleteAddress(
			@PathParam("customer-id") int customerId,
			@PathParam("address-id") int addressId)
	{
		addressService.deleteAddress(addressId);
		return Response.status(Status.NO_CONTENT).build();
	}
}

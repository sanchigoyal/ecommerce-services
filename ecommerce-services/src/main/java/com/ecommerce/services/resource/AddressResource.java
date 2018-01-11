package com.ecommerce.services.resource;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
import org.springframework.dao.EmptyResultDataAccessException;

import com.ecommerce.services.bean.Address;
import com.ecommerce.services.service.AddressService;


@Path("/customers/{customer-id}/addresses")
public class AddressResource {
	
	@Autowired
	private AddressService addressService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAddresses(
			@NotNull(message="{customer.id.notnull}")
			@Min(value=0, message="{customer.id.positive}")
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
			@NotNull(message="{customer.id.notnull}")
			@Min(value=0, message="{customer.id.positive}")
			@PathParam("customer-id") int customerId,
			@NotNull(message="{customer.address.notnull}")
			@Valid
			Address address,
			@Context UriInfo uriInfo)
	{
		addressService.addAddress(customerId, address);
		return Response.status(Status.CREATED)
			   .build();
	}
	
	@DELETE
	@Path("/{address-id}")
	public Response deleteAddress(
			@NotNull(message="{customer.id.notnull}")
			@Min(value=0, message="{customer.id.positive}")
			@PathParam("customer-id") int customerId,
			@NotNull(message="{customer.address.id.notnull}")
			@Min(value=0, message="{customer.address.id.positive}")
			@PathParam("address-id") int addressId)
	{
		try
		{
			// TODO - match for parent before deleting
			addressService.deleteAddress(addressId);
		}
		catch(EmptyResultDataAccessException e)
		{
			// do nothing - assume already deleted
		}
		return Response.status(Status.NO_CONTENT).build();
	}
}

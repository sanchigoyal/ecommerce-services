package com.ecommerce.services.resource;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
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


@DenyAll
@Path("/users")
public class AddressResource {
	
	@Autowired
	private AddressService addressService;
	
	@RolesAllowed("ADMIN")
	@GET
	@Path("/{user-id}/addresses")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAddresses(
			@NotNull(message="{user.id.notnull}")
			@Min(value=0, message="{user.id.positive}")
			@PathParam("user-id") int userId,
			@Context UriInfo uriInfo)
	{
		return Response.status(Status.OK)
				.entity(addressService.getAllAddresses(userId))
				.build();
	}
	
	@RolesAllowed("MEMBER")
	@GET
	@Path("/me/addresses")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMyAddresses(
			@NotNull(message="{user.id.notnull}")
			@Min(value=0, message="{user.id.positive}")
			@HeaderParam("USER-ID") int userId,
			@Context UriInfo uriInfo)
	{
		return Response.status(Status.OK)
				.entity(addressService.getAllAddresses(userId))
				.build();
	}
	
	@RolesAllowed("ADMIN")
	@POST
	@Path("/{user-id}/addresses")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addAddress(
			@NotNull(message="{user.id.notnull}")
			@Min(value=0, message="{user.id.positive}")
			@PathParam("user-id") int userId,
			@NotNull(message="{user.address.notnull}")
			@Valid
			Address address,
			@Context UriInfo uriInfo)
	{
		addressService.addAddress(userId, address);
		return Response.status(Status.CREATED)
			   .build();
	}
	
	@RolesAllowed("MEMBER")
	@POST
	@Path("/me/addresses")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addMyAddress(
			@NotNull(message="{user.id.notnull}")
			@Min(value=0, message="{user.id.positive}")
			@HeaderParam("USER-ID") int userId,
			@NotNull(message="{user.address.notnull}")
			@Valid
			Address address,
			@Context UriInfo uriInfo)
	{
		addressService.addAddress(userId, address);
		return Response.status(Status.CREATED)
			   .build();
	}
	
	@RolesAllowed("ADMIN")
	@DELETE
	@Path("/{user-id}/addresses/{address-id}")
	public Response deleteAddress(
			@NotNull(message="{user.id.notnull}")
			@Min(value=0, message="{user.id.positive}")
			@PathParam("user-id") int userId,
			@NotNull(message="{user.address.id.notnull}")
			@Min(value=0, message="{user.address.id.positive}")
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
	
	@RolesAllowed("MEMBER")
	@DELETE
	@Path("/me/addresses/{address-id}")
	public Response deleteMyAddress(
			@NotNull(message="{user.id.notnull}")
			@Min(value=0, message="{user.id.positive}")
			@HeaderParam("user-id") int userId,
			@NotNull(message="{user.address.id.notnull}")
			@Min(value=0, message="{user.address.id.positive}")
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

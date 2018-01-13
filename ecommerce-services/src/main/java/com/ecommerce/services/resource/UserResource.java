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

import com.ecommerce.services.bean.User;
import com.ecommerce.services.service.UserService;

@Path("/users")
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	@GET
	@Path("/{user-id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getuser(
			@NotNull(message="{user.id.notnull}")
			@Min(value=0, message="{user.id.positive}")
			@PathParam("user-id") int userId,
			@Context UriInfo uriInfo,
			@QueryParam("expand") String expand) 
	{
		User user = null;
		
		boolean doExpand = "ALL".equalsIgnoreCase(expand) ? true : false;
		
		user = userService.getUser(userId, uriInfo, doExpand);
		
		return Response.status(Status.OK)
				.entity(user)
				.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllusers(
			@Context UriInfo uriInfo,
			@QueryParam("expand") String expand)
	{
		List<User> users = null;

		boolean doExpand = "ALL".equalsIgnoreCase(expand) ? true : false;
		
		users = userService.getAllUsers(uriInfo, doExpand);
		
		return Response.status(Status.OK)
				.entity(users)
				.build();
		
	}

	public UserService getuserService() {
		return userService;
	}

	public void setuserService(UserService userService) {
		this.userService = userService;
	}
}

package com.ecommerce.services.resource;

import java.util.List;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import com.ecommerce.services.bean.Category;
import com.ecommerce.services.service.CategoryService;

@DenyAll
@Path("/product-categories")
public class CategoryResource {
	
	@Autowired
	CategoryService categoryService;
	
	@RolesAllowed("ADMIN")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCategory(
			@NotNull(message="{category.notnull}")
			@Valid
			Category category)
	{
		categoryService.addCategory(category);
		
		return Response.status(Status.CREATED).build();
	}
	
	@PermitAll
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCategory(
			@Context UriInfo uriInfo,
			@QueryParam("expand") String expand)
	{
		boolean doExpand = "ALL".equalsIgnoreCase(expand) ? true:false;
		List<Category> categories = categoryService.getAllCategory(uriInfo, doExpand);
		return Response.status(Status.OK)
				.entity(categories)
				.build();
	}
	
	@PermitAll
	@GET
	@Path("{product-category-id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategory(
			@Context UriInfo uriInfo,
			@NotNull(message="{category.id.notnull}")
			@Min(value=0, message="{category.id.positive}")
			@PathParam("product-category-id") int categoryId,
			@QueryParam("expand") String expand)
	{
		boolean doExpand = "ALL".equalsIgnoreCase(expand) ? true:false;
		Category category = categoryService.getCategory(uriInfo, doExpand, categoryId);
		return Response.status(Status.OK)
				.entity(category)
				.build();
	}
	
	@RolesAllowed("ADMIN")
	@DELETE
	@Path("{product-category-id}")
	public Response deleteCategory(
			@Context UriInfo uriInfo,
			@NotNull(message="{category.id.notnull}")
			@Min(value=0, message="{category.id.positive}")
			@PathParam("product-category-id") int categoryId)
	{
		try
		{
			categoryService.deleteCategory(categoryId);
		}
		catch(EmptyResultDataAccessException e)
		{
			// do nothing - assume already deleted
		}
		return Response.status(Status.NO_CONTENT).build();
	}
}

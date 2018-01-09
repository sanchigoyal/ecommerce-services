package com.ecommerce.services.resource;

import java.util.List;

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

import com.ecommerce.services.bean.Category;
import com.ecommerce.services.service.CategoryService;

@Path("/product-categories")
public class CategoryResource {
	
	@Autowired
	CategoryService categoryService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCategory(Category category)
	{
		categoryService.addCategory(category);
		
		return Response.status(Status.CREATED).build();
	}
	
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
	
	@GET
	@Path("{product-category-id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategory(
			@Context UriInfo uriInfo,
			@PathParam("product-category-id") int categoryId,
			@QueryParam("expand") String expand)
	{
		boolean doExpand = "ALL".equalsIgnoreCase(expand) ? true:false;
		Category category = categoryService.getCategory(uriInfo, doExpand, categoryId);
		return Response.status(Status.OK)
				.entity(category)
				.build();
	}
	
	@DELETE
	@Path("{product-category-id}")
	public Response deleteCategory(
			@Context UriInfo uriInfo,
			@PathParam("product-category-id") int categoryId)
	{
		categoryService.deleteCategory(categoryId);
		return Response.status(Status.NO_CONTENT).build();
	}
}

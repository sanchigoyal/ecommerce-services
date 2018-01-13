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

import com.ecommerce.services.bean.Product;

@Path("/product-categories/{product-category-id}/products")
public class ProductResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllProducts(
			@NotNull(message="{category.id.notnull}")
			@Min(value=0, message="{category.id.positive}")
			@PathParam("product-category-id") int categoryId,
			@Context UriInfo uriInfo)
	{
		return Response.status(Status.OK).build();
	}
	
	@GET
	@Path("/{product-id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProduct(
			@NotNull(message="{category.id.notnull}")
			@Min(value=0, message="{category.id.positive}")
			@PathParam("product-category-id") int categoryId,
			@NotNull(message="{category.product.id.notnull}")
			@Min(value=0, message="{category.product.id.positive}")
			@PathParam("product-id") int productId,
			@Context UriInfo uriInfo)
	{
		return Response.status(Status.OK).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addProduct(
			@NotNull(message="{category.id.notnull}")
			@Min(value=0, message="{category.id.positive}")
			@PathParam("product-category-id") int categoryId,
			@NotNull(message="{category.product.notnull")
			@Valid
			Product product)
	{
		return Response.status(Status.CREATED).build();
	}
	
	@DELETE
	@Path("/{product-id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteProduct(
			@NotNull(message="{category.id.notnull}")
			@Min(value=0, message="{category.id.positive}")
			@PathParam("product-category-id") int categoryId,
			@NotNull(message="{category.product.id.notnull}")
			@Min(value=0, message="{category.product.id.positive}")
			@PathParam("product-id") int productId)
	{
		return Response.status(Status.NO_CONTENT).build();
	}
}

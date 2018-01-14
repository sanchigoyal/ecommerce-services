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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import com.ecommerce.services.bean.Category;
import com.ecommerce.services.bean.Product;
import com.ecommerce.services.service.ProductService;

@DenyAll
@Path("/product-categories/{product-category-id}/products")
public class ProductResource {
	
	@Autowired
	private ProductService productService;
	
	@PermitAll
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllProducts(
			@NotNull(message="{category.id.notnull}")
			@Min(value=0, message="{category.id.positive}")
			@PathParam("product-category-id") int categoryId,
			@Context UriInfo uriInfo)
	{
		List<Product> products = productService.getAllProducts(categoryId, uriInfo);
		return Response.status(Status.OK)
				.entity(products)
				.build();
	}
	
	@PermitAll
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
		Product product = productService.getProduct(categoryId, productId, uriInfo);
		return Response.status(Status.OK)
				.entity(product)
				.build();
	}
	
	@RolesAllowed("ADMIN")
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
		product.setCategory(new Category(categoryId));
		productService.addProduct(product);
		return Response.status(Status.CREATED).build();
	}
	
	@RolesAllowed("ADMIN")
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
		try
		{
			productService.deleteProduct(productId);
		}
		catch(EmptyResultDataAccessException e)
		{
			// do nothing - assume already deleted
		}
		
		return Response.status(Status.NO_CONTENT).build();
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
}

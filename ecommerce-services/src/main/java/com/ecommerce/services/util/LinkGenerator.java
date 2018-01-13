package com.ecommerce.services.util;

import java.util.Map;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.ecommerce.services.resource.CategoryResource;
import com.ecommerce.services.resource.UserResource;
import com.ecommerce.services.resource.ProductResource;

public class LinkGenerator {
	
	public static String getResourceURL(UriInfo uriInfo, 
			Class<?> resourceClass,
			String currentResourceId,
			Map<String, String> pathParams,
			Map<String, String> queryParams)
	{
		UriBuilder uriBuilder= uriInfo.getBaseUriBuilder();
		uriBuilder = uriBuilder.path(resourceClass);
		
		if(currentResourceId != null)
		{
			uriBuilder.path(currentResourceId);
		}
		
		if(pathParams != null)
		{
			for(String template : pathParams.keySet())
			{
				uriBuilder.resolveTemplate(template, pathParams.get(template));
			}
		}
		
		if(queryParams != null)
		{
			for(String param : queryParams.keySet())
			{
				uriBuilder.queryParam(param, queryParams.get(param));
			}
		}
		
		return uriBuilder.build().toString();
		
	}
	
	public static String getUserResourceInstanceURL(UriInfo uriInfo, int customerId)
	{
		String url = uriInfo.getBaseUriBuilder()
						.path(UserResource.class)
						.path(String.valueOf(customerId))
						.build()
						.toString();
		return url;
	}

	public static String getCategoryResourceInstanceURL(UriInfo uriInfo, int categoryId) {
		String url = uriInfo.getBaseUriBuilder()
				.path(CategoryResource.class)
				.path(String.valueOf(categoryId))
				.build()
				.toString();
		return url;
	}

	public static String getProductResourceCollectionURL(UriInfo uriInfo, int categoryId) {
		String url = uriInfo.getBaseUriBuilder()
				.path(ProductResource.class)
				.resolveTemplate("product-category-id", categoryId)
				.build()
				.toString();
		return url;
	}
}

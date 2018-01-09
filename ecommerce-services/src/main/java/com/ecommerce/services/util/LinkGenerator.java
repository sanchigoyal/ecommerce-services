package com.ecommerce.services.util;

import javax.ws.rs.core.UriInfo;

import com.ecommerce.services.resource.CategoryResource;
import com.ecommerce.services.resource.CustomerResource;
import com.ecommerce.services.resource.ProductResource;

public class LinkGenerator {
	
	public static String getCustomerResourceInstanceURL(UriInfo uriInfo, int customerId)
	{
		String url = uriInfo.getBaseUriBuilder()
						.path(CustomerResource.class)
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

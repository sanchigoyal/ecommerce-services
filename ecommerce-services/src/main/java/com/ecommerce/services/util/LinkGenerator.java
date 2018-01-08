package com.ecommerce.services.util;

import javax.ws.rs.core.UriInfo;

import com.ecommerce.services.resource.CustomerResource;

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
}

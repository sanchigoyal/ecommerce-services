package com.ecommerce.services;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.ecommerce.services.exception.ConstraintViolationExceptionMapper;
import com.ecommerce.services.exception.RecordNotFoundExceptionMapper;
import com.ecommerce.services.resource.AddressResource;
import com.ecommerce.services.resource.CategoryResource;
import com.ecommerce.services.resource.UserResource;
import com.ecommerce.services.resource.ProductResource;

@Component
@ApplicationPath("/ecommerce-services")
public class JerseyConfig extends ResourceConfig{
	
	public JerseyConfig()
	{
		register(UserResource.class);
		register(AddressResource.class);
		register(CategoryResource.class);
		register(ProductResource.class);
		register(RecordNotFoundExceptionMapper.class);
		register(ConstraintViolationExceptionMapper.class);
	}
}

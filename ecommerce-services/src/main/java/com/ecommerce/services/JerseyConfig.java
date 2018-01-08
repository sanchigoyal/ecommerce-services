package com.ecommerce.services;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.ecommerce.services.resource.AddressResource;
import com.ecommerce.services.resource.CustomerResource;

@Component
@ApplicationPath("/ecommerce-services")
public class JerseyConfig extends ResourceConfig{
	
	public JerseyConfig()
	{
		register(CustomerResource.class);
		register(AddressResource.class);
	}
}

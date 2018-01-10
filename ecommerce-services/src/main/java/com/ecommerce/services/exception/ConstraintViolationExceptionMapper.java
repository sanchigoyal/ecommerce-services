package com.ecommerce.services.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.ecommerce.services.bean.ErrorMessage;
import com.ecommerce.services.bean.ErrorProperty;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

	private static final String HTTP_400_BAD_REQUEST = "400";
	private static final String VERIFY_REQUEST_ATTRIBUTES_MESSAGE = "verify request attributes";
	private static final String CONSTRAINT_VOILATION_MESSAGE = "constraint voilation";

	@Override
	public Response toResponse(ConstraintViolationException ex) {
		
		Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
		ErrorMessage message = new ErrorMessage(
				1000, 
				CONSTRAINT_VOILATION_MESSAGE, 
				VERIFY_REQUEST_ATTRIBUTES_MESSAGE, 
				HTTP_400_BAD_REQUEST);
		violations.forEach(item->{
			message.getProperties()
				.add(new ErrorProperty(
						item.getPropertyPath().toString(),
						item.getMessage(),
						item.getInvalidValue().toString()));
		});
		
		return Response.status(Status.BAD_REQUEST)
				.entity(message)
				.build();
	}

}

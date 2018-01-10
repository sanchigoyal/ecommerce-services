package com.ecommerce.services.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.ecommerce.services.bean.ErrorMessage;
import com.ecommerce.services.bean.ErrorProperty;

@Provider
public class RecordNotFoundExceptionMapper implements ExceptionMapper<RecordNotFoundException> {

	private static final String HTTP_404_NOT_FOUND = "404";
	private static final String ID_MAY_BE_INVALID_MESSAGE = "id may be invalid";
	private static final String RECORD_NOT_FOUND_MESSAGE = "record not found";

	@Override
	public Response toResponse(RecordNotFoundException ex) {
		ErrorMessage message = new ErrorMessage(
				1001,
				RECORD_NOT_FOUND_MESSAGE, 
				ID_MAY_BE_INVALID_MESSAGE, 
				HTTP_404_NOT_FOUND);
		
		message.getProperties().add(new ErrorProperty(
				"", 
				ex.getMessage(),
				String.valueOf(ex.getRecordId())));
		
		return Response.status(Status.NOT_FOUND)
				.entity(message)
				.build();
	}

}

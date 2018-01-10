package com.ecommerce.services.exception;

public class RecordNotFoundException  extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int recordId;
	
	public RecordNotFoundException(int recordId, String message)
	{
		super(message);
		this.recordId = recordId;
	}

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}
}

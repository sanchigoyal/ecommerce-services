package com.ecommerce.services.bean;

import java.util.ArrayList;
import java.util.List;

public class ErrorMessage {
	
	private int code;
	private String message;
	private String developerMessage;
	private String responseCode;
	private List<ErrorProperty> properties = new ArrayList<ErrorProperty>();
	
	
	public ErrorMessage(int code, String message, String developerMessage, String responseCode) {
		super();
		this.code = code;
		this.message = message;
		this.developerMessage = developerMessage;
		this.responseCode = responseCode;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDeveloperMessage() {
		return developerMessage;
	}
	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public List<ErrorProperty> getProperties() {
		return properties;
	}
	public void setProperties(List<ErrorProperty> properties) {
		this.properties = properties;
	}
	
	
	
}

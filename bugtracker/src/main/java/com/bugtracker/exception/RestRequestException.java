package com.bugtracker.exception;

import org.springframework.http.HttpStatus;

public class RestRequestException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6183799614204805472L;

	private HttpStatus httpStatus;
	private String errorCode;
	private String errorMessage;
	public RestRequestException(HttpStatus httpStatus, String errorCode, String errorMessage) {
		
		super(errorMessage);
		this.httpStatus = httpStatus;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}

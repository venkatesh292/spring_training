package com.bugtracker.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(value = RestRequestException.class)
	public ResponseEntity<Object> handleRestRequestException(final RestRequestException restRequestException, HttpServletRequest httpServletRequest) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode(restRequestException.getErrorCode());
		errorResponse.setErrorMessage(restRequestException.getErrorMessage());
		errorResponse.setRequestUri(httpServletRequest.getRequestURI());
		
		return new ResponseEntity<>(errorResponse,new HttpHeaders(),restRequestException.getHttpStatus());
	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> handleGenericException(final Exception exception, HttpServletRequest httpServletRequest){
		
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode("110000");
		errorResponse.setErrorMessage(exception.toString());
		errorResponse.setRequestUri(httpServletRequest.getRequestURI());
		
		return new ResponseEntity<>(errorResponse,new HttpHeaders(),HttpStatus.REQUEST_TIMEOUT);
	}
	
	

}

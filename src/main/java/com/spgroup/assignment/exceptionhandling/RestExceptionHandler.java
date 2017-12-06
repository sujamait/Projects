package com.spgroup.assignment.exceptionhandling;

import java.util.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.*;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	/**
     * Handle MethodArgumentNotValidException. Triggered when an object fails @Valid validation.
     *
     * @param ex      the MethodArgumentNotValidException that is thrown when @Valid validation fails
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ResponseEntity object
     */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
	  MethodArgumentNotValidException ex, 
	  HttpHeaders headers, 
	  HttpStatus status, 
	  WebRequest request) {
	    List<String> errors = new ArrayList<String>();
	    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
	        errors.add(error.getField() + ": " + error.getDefaultMessage());
	    }
	    for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
	        errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
	    }
	     
	    FriendManagementApplicationException apiError = 
	      new FriendManagementApplicationException(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
	    return new ResponseEntity<Object>(apiError, headers, apiError.getStatus());
	}
	
	 /**
     * Handle MissingServletRequestParameterException. Triggered when a 'required' request parameter is missing.
     *
     * @param ex      MissingServletRequestParameterException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ResponseEntity object
     */
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
	  MissingServletRequestParameterException ex, HttpHeaders headers, 
	  HttpStatus status, WebRequest request) {
	    String error = ex.getParameterName() + " parameter is missing";
	     
	    FriendManagementApplicationException apiError = 
	      new FriendManagementApplicationException(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
	    return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	 /**
     * Handles javax.validation.ConstraintViolationException. Thrown when @Validated fails.
     *
     * @param ex the ConstraintViolationException
     * @return the ResponseEntity object
     */
	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolation(
	  ConstraintViolationException ex, WebRequest request) {
	    List<String> errors = new ArrayList<String>();
	    for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
	        errors.add(violation.getRootBeanClass().getName() + " " + 
	          violation.getPropertyPath() + ": " + violation.getMessage());
	    }
	 
	    FriendManagementApplicationException apiError = 
	      new FriendManagementApplicationException(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
	    return new ResponseEntity<Object>(apiError,apiError.getStatus());
	}
	
	  /**
     * Handle Exception, handle generic MethodArgumentTypeMismatchException.class
     *
     * @param ex the Exception
     * @return the ResponseEntity object
     */
	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
	  MethodArgumentTypeMismatchException ex, WebRequest request) {
	    String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();
	 
	    FriendManagementApplicationException apiError = 
	      new FriendManagementApplicationException(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
	    return new ResponseEntity<Object>(apiError, apiError.getStatus());
	}
	
	
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
	  HttpMediaTypeNotSupportedException ex, 
	  HttpHeaders headers, 
	  HttpStatus status, 
	  WebRequest request) {
	    StringBuilder builder = new StringBuilder();
	    builder.append(ex.getContentType());
	    builder.append(" media type is not supported. Supported media types are ");
	    ex.getSupportedMediaTypes().forEach(t -> builder.append(t + ", "));
	 
	    FriendManagementApplicationException apiError = new FriendManagementApplicationException(HttpStatus.UNSUPPORTED_MEDIA_TYPE, 
	      ex.getLocalizedMessage(), builder.substring(0, builder.length() - 2));
	    return new ResponseEntity<Object>(apiError, apiError.getStatus());
	}
	
	@ExceptionHandler({FriendManagementApplicationException.class })
	public ResponseEntity<Object> handleApplicationException(FriendManagementApplicationException ex, WebRequest request) {
	    FriendManagementApplicationException apiError = new FriendManagementApplicationException(
	      HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "application error occurred");
	    return new ResponseEntity<Object>(apiError, apiError.getStatus());
	}
	
	@ExceptionHandler({Exception.class })
	public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
	    FriendManagementApplicationException apiError = new FriendManagementApplicationException(
	      HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "error occurred");
	    return new ResponseEntity<Object>(apiError, apiError.getStatus());
	}

}
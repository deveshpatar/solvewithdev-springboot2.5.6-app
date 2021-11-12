package com.solvewithdev.exception;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author dpatar
 *
 */
@ControllerAdvice
@RestController
public class MySpringBootExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(MySpringBootExceptionHandler.class);

	/**
	 * Handle HttpStatusCodeExceptions.
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */

	@ExceptionHandler(HttpStatusCodeException.class)
	public final ResponseEntity<Response> handleHttpStatusExceptions(HttpStatusCodeException ex, WebRequest request) {
		Response apiResponse = new Response();
		if (ex.getRawStatusCode() == 404) {
			apiResponse.setResponseMetaData(
					this.createResponseMetaData(HttpStatus.NOT_FOUND.value(), ex.getMessage(), "Not Found."));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
		} else if (ex.getRawStatusCode() == 204) {
			apiResponse.setResponseMetaData(
					this.createResponseMetaData(HttpStatus.NO_CONTENT.value(), ex.getMessage(), "No Content."));
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(apiResponse);
		} else if (ex.getRawStatusCode() == 400) {
			apiResponse.setResponseMetaData(this.createResponseMetaData(HttpStatus.BAD_REQUEST.value(),
					ex.getMessage(), "Bad Request."));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
		} else if (ex.getRawStatusCode() == 504) {
			apiResponse.setResponseMetaData(this.createResponseMetaData(HttpStatus.GATEWAY_TIMEOUT.value(),
					ex.getMessage(), "Gateway Time-out."));
			return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(apiResponse);
		} else {
			apiResponse.setResponseMetaData(this.createResponseMetaData(
					HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), "Internal Server Error."));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
		}
	}

	/**
	 * Handle Generic Exceptions.
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Response> handleRuntimeExceptions(Exception ex, WebRequest request) {
		Response apiResponse = new Response();
		LOG.error("Exception Occured While processing the request:{}", ex);
		apiResponse.setResponseMetaData(this.createResponseMetaData(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				ex.getMessage(), "Internal Server Error while processing the request."));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
	}

	/**
	 * Handle Invalid Inputs to APIs
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Response apiResponse = new Response();
		LOG.error("Input Fields not provided as a part of the API Request", ex);
		List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
		List<String> inputErrorMessages = new ArrayList<>();
		for (ObjectError error : allErrors) {
			if (error.getDefaultMessage() != null) {
				inputErrorMessages.add(error.getDefaultMessage());
			}
		}
		apiResponse.setResponseMetaData(
				this.createResponseMetaData(HttpStatus.BAD_REQUEST.value(), inputErrorMessages.toArray(new String[0])));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
	}
	
	/**
	 * Create ResponseMetaData Object.
	 * 
	 * @param statusCode
	 * @param messages
	 * @return
	 */
	private ResponseMetaData createResponseMetaData(int statusCode, String... messages) {
		ResponseMetaData responseMetaData = new ResponseMetaData();
		responseMetaData.setRespCode(String.valueOf(statusCode));
		List<String> respMessages = new ArrayList<>();
		Collections.addAll(respMessages, messages);
		responseMetaData.setRespMessages(respMessages);
		return responseMetaData;
	}

}

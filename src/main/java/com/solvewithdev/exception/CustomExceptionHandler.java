package com.solvewithdev.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler({RecordNotFoundException.class, ConnectionNotFoundException.class})
	public ResponseEntity<Map<String, String>> handleException(RuntimeException e) {
		Map<String, String> error = new HashMap<>();
		error.put("message", e.getMessage());
		
		return new ResponseEntity<Map<String, String>>(error, HttpStatus.BAD_REQUEST);
	}

}

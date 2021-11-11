package com.solvewithdev.exception;

public class RecordNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6605395271899404242L;

	public RecordNotFoundException(String message) {
		super(message);
	}
}

package com.solvewithdev.exception;

public class ConnectionNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4109143457162737734L;

	public ConnectionNotFoundException(String message) {
		super(message);
	}
}

package com.com.yummigr.dtos;

/**
 * Simple Dto for response entity forbbiden 403;
 * @author osvaldoairon
 *
 */
public class FailureCreateUser {
	
	private String message;
	
	/**
	 * default;
	 */
	public FailureCreateUser(String message) {
		setMessage(message);
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

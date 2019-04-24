package com.stackroute.accountmanager.exceptions;

@SuppressWarnings("serial")
public class UserAlreadyExistsException extends Exception {

	public UserAlreadyExistsException(String message) {
		super(message);
		
	}
}

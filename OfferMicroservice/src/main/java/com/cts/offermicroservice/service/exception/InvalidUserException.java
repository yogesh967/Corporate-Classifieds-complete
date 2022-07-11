package com.cts.offermicroservice.service.exception;

public class InvalidUserException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public InvalidUserException(String msg) {
		super(msg);
		
	}

}

package com.sidd.proj.PersonAddress.CustomErrorHandling;

public class DataNotFoundException extends Exception{

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public DataNotFoundException() {
		super();
	}

	public DataNotFoundException(String message) {
		super(message);
	}

	public DataNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataNotFoundException(Throwable cause) {
		super(cause);
	}

	protected DataNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
}

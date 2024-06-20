package com.sidd.proj.PersonAddress.CustomErrorHandling;

public class DataMatchedException extends Exception {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public DataMatchedException() {
		super();
	}

	public DataMatchedException(String message) {
		super(message);
	}

	public DataMatchedException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataMatchedException(Throwable cause) {
		super(cause);
	}

	protected DataMatchedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

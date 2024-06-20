package com.sidd.proj.PersonAddress.CustomErrorHandling;

public class NoRecordsAvaliable extends Exception{

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public NoRecordsAvaliable() {
		super();
	}

	public NoRecordsAvaliable(String message) {
		super(message);
	}

	public NoRecordsAvaliable(String message, Throwable cause) {
		super(message, cause);
	}

	public NoRecordsAvaliable(Throwable cause) {
		super(cause);
	}

	protected NoRecordsAvaliable(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	
}

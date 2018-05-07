package com.menglin.tripro.util;

public class MySqlException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	public MySqlException(Throwable cause) {
		super(cause);
	}
	public MySqlException(String message, Throwable cause) {
		super(message, cause);
	}
}

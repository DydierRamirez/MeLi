package com.meli.ipvalidator.exception;

public class FallbackMethodException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

    public FallbackMethodException(String message) {
        super(message);
    }

    public FallbackMethodException(String message, Throwable cause) {
        super(message, cause);
    }

    public FallbackMethodException(Throwable cause) {
        super(cause);
    }

}

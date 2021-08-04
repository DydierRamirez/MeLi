package com.meli.ipvalidator.exception;

public class CallLegacyException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

    public CallLegacyException(String message) {
        super(message);
    }

    public CallLegacyException(String message, Throwable cause) {
        super(message, cause);
    }

    public CallLegacyException(Throwable cause) {
        super(cause);
    }
}

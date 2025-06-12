package dev.caobaoqi.backend.exception;

import java.io.Serial;

public class BusinessException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = -6324446299169584065L;

	public BusinessException() {
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(
		String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}

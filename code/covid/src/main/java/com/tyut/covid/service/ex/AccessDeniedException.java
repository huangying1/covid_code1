package com.tyut.covid.service.ex;

/**
 * 访问被拒绝异常
 *
 */
public class AccessDeniedException extends ServiceException {
	
	private static final long serialVersionUID = 1L;

	public AccessDeniedException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccessDeniedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public AccessDeniedException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public AccessDeniedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public AccessDeniedException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	
	
}

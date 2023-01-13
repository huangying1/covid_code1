package com.tyut.covid.core.exception;

/**
 * 没有权限异常
 */
public class NotAuthorizedException extends BusinessException {

	private static final long serialVersionUID = -8955362618359481710L;

	public NotAuthorizedException() {
		super(401, "Not Authorized");
	}

	public NotAuthorizedException(String message) {
		super(401, message);
	}

}

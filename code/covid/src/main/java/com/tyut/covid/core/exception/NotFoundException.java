package com.tyut.covid.core.exception;

/**
 * 无资源异常
 */
public class NotFoundException extends BusinessException {

	private static final long serialVersionUID = -4947677104816905438L;

	public NotFoundException() {
		super(404, "Not Found");
	}

	public NotFoundException(String message) {
		super(404, message);
	}

}

package com.tyut.covid.service.ex;

/**
 * 购物车商品信息不存在
 *
 */
public class CartNotFoundException extends ServiceException {
	
	
	private static final long serialVersionUID = 1L;

	public CartNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public CartNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CartNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CartNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	
	
}

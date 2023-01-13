package com.tyut.covid.controller;

import com.tyut.covid.controller.ex.*;
import com.tyut.covid.service.ex.*;
import com.tyut.covid.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * 控制器类的基类，实现统一处理异常
 *
 */
public class BaseController {
	/**
	 * 操作结果的成功状态
	 */
	public static final Integer SUCCESS = 2000;
	
	// 处理 ServiceException, FileUploadException 两大类异常
	@ExceptionHandler({ServiceException.class, FileUploadException.class})
	public JsonResult<Void> handleException(Throwable e){
		JsonResult<Void> jr = new JsonResult<Void>();
		jr.setMessage(e.getMessage());
		if(e instanceof UsernameDuplicateException) {
			jr.setState(4000);
		}else if(e instanceof UserNotFoundException) {
			jr.setState(4001);
		}else if(e instanceof PasswordNotMatchException) {
			jr.setState(4002);
		}else if(e instanceof FileEmptyException) {
			jr.setState(4003);
		}else if(e instanceof FileSizeException) {
			jr.setState(4004);
		}else if(e instanceof FileTypeException) {
			jr.setState(4005);
		}else if(e instanceof FileUploadStateException) {
			jr.setState(4006);
		}else if(e instanceof FileUploadIOException) {
			jr.setState(4007);
		}else if(e instanceof AddressNotFoundException) {
			jr.setState(4008);
		}else if(e instanceof AccessDeniedException) {
			jr.setState(4009);
		}else if(e instanceof CartNotFoundException) {
			jr.setState(4010);
		}else if(e instanceof InsertException) {
			jr.setState(5000);
		}else if(e instanceof UpdateException) {
			jr.setState(5001);
		}else if(e instanceof DeleteException) {
			jr.setState(5002);
		}
		
		return jr;
	}
	
	/**
	 * 从 session 中获取 uid
	 * @param session
	 * @return
	 * 添加 final 表示该方法不可以被重写
	 */
	protected final Integer getUidFromSession(HttpSession session) {
		return Integer.valueOf(session.getAttribute("uid").toString());
	}
	
	/**
	 * 从 session 中获取 username
	 * @param session
	 * @return
	 * 添加 final 表示该方法不可以被重写
	 */
	protected final String getUsernameFromSession(HttpSession session) {
		return session.getAttribute("username").toString();
	}
	
	
}

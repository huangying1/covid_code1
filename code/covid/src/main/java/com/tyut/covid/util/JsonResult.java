package com.tyut.covid.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;

/**
 * 向客户端响应操作结果的数据类型
 * 
 * @author DELL
 *
 * @param <T>
 */
@JsonInclude(Include.NON_NULL)
public class JsonResult<T> {

	private Integer state; // 200   404

	private String message; // 成功   没有找到

	private T data;         //  User{name，phone}    null

    public JsonResult(int i, String 成功, List<T> dataList) {
    }

    public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public JsonResult() {
		
	}

	public JsonResult(Integer state) {
		super();
		this.state = state;
	}

	public JsonResult(Integer state, T data) {
		super();
		this.state = state;
		this.data = data;
	}
	
	
	
	

}

package com.ydm.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
@SuppressWarnings("rawtypes")
public class BaseRespObject implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final Integer STATUS_1 = 1; // 成功
	public static final Integer STATUS_0 = 0; // 失败
	private Integer status;//0 失败 1成功
	private String message;//消息
	private Object data;//返回结果集
	
	public BaseRespObject() {

	}

	public BaseRespObject(Integer status,String message) {
		this.status = status;
		this.message = message;
		if (data == null) {
			data = new HashMap();
		}
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	
	
}

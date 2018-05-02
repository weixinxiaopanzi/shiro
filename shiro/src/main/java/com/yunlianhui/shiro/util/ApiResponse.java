package com.yunlianhui.shiro.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * API接口报文响应通用数据载体
 *
 */
@Component
public class ApiResponse {
	//返回码
	private  String code;
	//返回消息
	private String message;
	//响应时间
	private String date = DateUtil.getStringDate();
	
	/** 报文响应内容信息 */
	public Map<String, Object> data = new HashMap<String, Object>();


    public ApiResponse setCodeAndMessage(String code,String message){
    	this.code=code;
    	this.message=message;
    	return this;
    }
    public void putData(String key,Object value){
    	this.data.put(key,value);
	}

	public String getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public void setCode(String code) {
		this.code = code;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setData(HashMap<String, Object> data) {
		this.data = data;
	}
	
	public ApiResponse success(String message){
    	this.code=UM.CODE_SUCCESS_0000;
    	this.message=message;
    	return this;
    }
	
	public ApiResponse success(String message,HashMap<String, Object> data){
    	this.code=UM.CODE_SUCCESS_0000;
    	this.message=message;
    	this.data = data;
    	return this;
    }
	
	public ApiResponse failue(String message){
    	this.code=UM.CODE_ERROR_9999;
    	this.message=message;
    	return this;
    }

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * 返回此对象所标识的json字符串
	 */
	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (Exception e) {
			throw new RuntimeException("对象转json字符串出错, " + e.getMessage() , e);
		}
	}
}

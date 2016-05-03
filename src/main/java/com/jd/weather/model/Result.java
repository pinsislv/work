package com.jd.weather.model;

public class Result {
	public static int CODE_SUCCESS = 1;
	public static int CODE_FAIL = 2;
	private int code;
	private Object Data;
	
	public static Result success(Object object){
		Result result = new Result();
		result.setCode(CODE_SUCCESS);
		result.setData(object);
		return result;
	}
	public static Result fail(String errorMsg){
		Result result = new Result();
		result.setCode(CODE_FAIL);
		result.setData(errorMsg);
		return result;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Object getData() {
		return Data;
	}
	public void setData(Object data) {
		Data = data;
	}
}

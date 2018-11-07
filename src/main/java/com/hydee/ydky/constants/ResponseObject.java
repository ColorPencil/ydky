package com.hydee.ydky.constants;

import java.io.Serializable;

import com.hydee.ydky.utils.StringUtils;

public final class ResponseObject<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer code = 200;
	
	private T data;
	
	private String msg = "";
	
	private String err = "";
	
	public ResponseObject() {}
	
	public ResponseObject(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public ResponseObject(Integer code, String msg, String err) {
		this.code = code;
		this.msg = msg;
		this.err = err;
	}
	
	public ResponseObject(Integer code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	public Integer getCode() {
		return code;
	}

	public ResponseObject<T> setCode(Integer code) {
		this.code = code;
		return this;
	}

	public T getData() {
		return data;
	}

	public ResponseObject<T> setData(T data) {
		this.data = data;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public ResponseObject<T> setMsg(String msg) {
		this.msg = msg;
		return this;
	}
	
	public ResponseObject<T> setMsg(String msg, Object...args) {
		this.msg = String.format(msg, args);
		return this;
	}

	public String getErr() {
		return err;
	}

	public ResponseObject<T> setErr(String err) {
		this.err = err;
		return this;
	}
	
	public ResponseObject<T> setResult(Results result) {
		this.code = result.getCode();
		this.msg = result.getMsg();
		return this;
	}

	/**
	 * 返回请求成功对象
	 * @return
	 */
	public static ResponseObject<Object> Success() {
		return Create(Results.COM_SUCCESS);
	}
	
	/**
	 * 返回请求成功对象(带数据返回的)
	 * @param data
	 * @return
	 */
	public static ResponseObject<Object> Success(Object data) {
		return Create(Results.COM_SUCCESS, data);
	}
	
	/**
	 * 返回请求失败对象
	 * @return
	 */
	public static ResponseObject<Object> Failed() {
		return Create(Results.COM_FAILD);
	}
	
	/**
	 * 返回请求失败对象(带堆栈异常信息的)
	 * @param err
	 * @return
	 */
	public static ResponseObject<Object> Failed(Throwable err) {
		String errors = err.getCause() == null ? err.getMessage() : err.getCause().getMessage();
		if(StringUtils.isEmpty(errors)) {
			errors = StringUtils.getStackTrace(err);
			int errorsLength = errors.indexOf("at ");
			errors = errors.substring(0, errorsLength > 0 ? errorsLength : errors.length()).replaceAll("[\r\n\t]+", "\n");
		}
		return Create(Results.COM_FAILD, errors);
	}
	
	/**
	 * 返回因必传参数错误而请求失败的对象
	 * @param err
	 * @return
	 */
	public static ResponseObject<Object> FailedToParams(String...args) {
		StringBuffer params = new StringBuffer();
		for (String string : args) params.append(string).append(",");
		return Create(Results.COM_REQUEST_PARAMS_NOT_FULL).setMsg(String.format(Results.COM_REQUEST_PARAMS_NOT_FULL.getMsg(), params.deleteCharAt(params.length()-1).toString()));
	}
	
	/**
	 * 根据结果集返回请求对象
	 * @param result
	 * @return
	 */
	public static ResponseObject<Object> Create(Results result) {
		return Create(result, null);
	}
	
	/**
	 * 根据结果集返回请求对象(带数据返回的)
	 * @param result
	 * @param data
	 * @return
	 */
	public static ResponseObject<Object> Create(Results result, Object data) {
		return new ResponseObject<Object>(result.getCode(), result.getMsg(), data);
	}
	
	/**
	 * 根据结果集返回请求对象(带堆栈异常信息的)
	 * @param result
	 * @param errors
	 * @return
	 */
	public static ResponseObject<Object> Create(Results result, String errors) {
		return new ResponseObject<Object>(result.getCode(), result.getMsg(), errors);
	}
}


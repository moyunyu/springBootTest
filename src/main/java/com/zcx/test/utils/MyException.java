package com.zcx.test.utils;

public class MyException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4817738044343925932L;

	private String errorCode;

	private String errorMsg;

	public MyException() {
		super();
	}

	public MyException(String errorCode, String errorMsg) {
		this.setErrorCode(errorCode);
		this.setErrorMsg(errorMsg);
	}

	public MyException(String errorCode, String errorMsg, Throwable e) {
		super(e);
		this.setErrorCode(errorCode);
		this.setErrorMsg(errorMsg);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}

package com.woshua.core.web;

public class BussinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	/**
	 * 错误码
	 */
	private ErrorCode errorCode;

	public BussinessException() {
		super();
		this.errorCode = ErrorCode.ERRORCODE_UNKNOW_ERROR;
	}

	public BussinessException(String message, Throwable cause, ErrorCode errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public BussinessException(String message, ErrorCode errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	public BussinessException(ErrorCode errorCode) {
		super(errorCode.getDesc());
		this.errorCode = errorCode;
	}

	public BussinessException(Throwable cause, ErrorCode errorCode) {
		super(cause);
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

}

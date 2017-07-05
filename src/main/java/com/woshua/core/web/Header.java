/*
 * @(#)Header.java
 *
 * Copyright 2013 vision, Inc. All rights reserved.
 */

package com.woshua.core.web;


/**
 * 返回的头信息
 * 
 * @author fyuan
 * @version 1.0,2013-4-2
 */
public class Header {
	private int flag;
	private int errorCode;
	private String errorDesc;

	public Header(int flag, int errorCode, String errorDesc) {
		this.flag = flag;
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
	}

	public Header(ErrorCode errorCode) {
		this.flag = -1;
		this.errorCode = errorCode.getValue();
		this.errorDesc = errorCode.getDesc();
	}
	
	public Header() {
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}
}

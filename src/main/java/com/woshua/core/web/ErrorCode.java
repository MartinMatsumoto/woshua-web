package com.woshua.core.web;

public enum ErrorCode {

	/** 成功 */
	ERRORCODE_SUCCESS(1, "成功"),
	/** 未知错误 */
	ERRORCODE_UNKNOW_ERROR(2, "未知错误"),
	/** 未登录 */
	ERRORCODE_UN_LOGIN(3, "您还未登录"),
	/** 对象不存在 */
	ERRORCODE_TARGET_NOT_EXIST(4, "对象不存在"),
	/** 对象已存在 */
	ERRORCODE_TARGET_EXIST(5, "对象已存在"),
	/** 账号或邮箱已存在 */
	ERRORCODE_REGISTER_EXIST(6, "账号或邮箱已存在"),
	/** 注册失败 */
	ERRORCODE_REGISTER_ERROR(7, "注册失败，请重试。"),
	/** 账号或邮箱已存在 */
	ERRORCODE_LOGIN_ERROR(8, "账号不存在或密码错误");

	private int value;
	private String desc;

	ErrorCode(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public int getValue() {
		return this.value;
	}

	public String getDesc() {
		return this.desc;
	}

	public static ErrorCode get(int value) {
		for (ErrorCode errorCode : ErrorCode.values()) {
			if (errorCode.value == value) {
				return errorCode;
			}
		}
		throw new IllegalArgumentException("argument error: " + value);
	}

}
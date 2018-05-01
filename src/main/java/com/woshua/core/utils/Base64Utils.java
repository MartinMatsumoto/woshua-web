/**
 * @(#)Codec.java
 *
 * Copyright 2010 vision, Inc. All rights reserved.
 */
package com.woshua.core.utils;

import com.woshua.core.web.BussinessException;
import com.woshua.core.web.ErrorCode;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import java.io.*;


/**
 * description
 * @author  fengyuan
 * @version 1.0,2010-11-10
 */
public class Base64Utils {
	
	static Logger logger = Logger.getLogger(Base64Utils.class);
	
	public static String encoding(String value){
		try {
			byte[] bs = Base64.encodeBase64(value.getBytes("UTF-8"));
			return new String(bs,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
			throw new RuntimeException("encode error.",e);
		}
		
	}
	public static String decodeing(String encodingValue){
		try {
			byte[] bs = Base64.decodeBase64(encodingValue);
			return new String(bs,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
			throw new RuntimeException("decode error.",e);
		}
	}
	
	public static String encodeingSafe(String value) {
		try{
			byte[] bs = Base64.encodeBase64(value.getBytes("UTF-8"), true);
			return new String(bs,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
			throw new RuntimeException("encode error.",e);
		}
	}
	
	public static String urlEncoding(String value){
		try{
			byte[] bs = Base64.encodeBase64URLSafe(value.getBytes("UTF-8"));
			return new String(bs,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
			throw new RuntimeException("encode error.",e);
		}
	}
	
	/**
	 * 解码base64格式的文件
	 * 
	 * @param destCode 编码后的字符串
	 * @param destPath 保存文件的绝对路劲
	 */
	public static void DecodeToFile(String destCode, String destPath) {
		try {
			FileOutputStream fos = new FileOutputStream(new File(destPath));
			fos.write(Base64.decodeBase64(destCode));
			fos.flush();
			fos.close();
		} catch (IOException e) {
			logger.error(e);
			throw new BussinessException("文件上传失败",e,ErrorCode.ERRORCODE_UPLOADFILE_ERROR);
		}
	}
}

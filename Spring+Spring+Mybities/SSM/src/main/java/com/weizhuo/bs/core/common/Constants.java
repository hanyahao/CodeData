package com.weizhuo.bs.core.common;

import java.util.regex.Pattern;

/**
 * 常量
 */
public final class Constants {
	
	private Constants() {}

	//IP
	public static final Pattern IP_PATTERN = Pattern.compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
	//特殊字符
	public static final Pattern CHARFILTER_PATTERN = Pattern.compile("[`~!@#$%^&*()+-=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]");


	public static final String OK = "OK";
	public static final String WEBSOCKET_IS_CONNECT = "IS_CONNECT";
	public static final String OPEN_CONNECT = "OPEN_CONNECT";


	

}
package com.weizhuo.bs.util;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.weizhuo.bs.core.common.AppConfig;


public class SignUtil {
	public static final String DIGEST_MD5 = "1";
	public static final String DIGEST_SHA1 = "2";
	public static final String DIGEST_SHA256 = "3";
	public static final String DIGEST_SHA512 = "4";
	public static final int AL_TOTOAL = 4;
	
	public static final int SIGN_LENGTH = 16;
	
	public static final Pattern LONG_PATTERN = Pattern.compile("^[1-9]+\\d*$");
    public static final String charset = "UTF-8";
	
	/**======================= 客户端生成签名  begin =============================================*/
    /*-------------------随机加密,对所有参数加密--------------*/
    //随机加密,截取默认长度
	public static String getRandomSignStr(String app_id, String app_token, Map<String,String> params){
		return getRandomSignSub(app_id, app_token, params, SIGN_LENGTH);
	}
	
	//随机加密,并截取指定长度字符串
	public static String getRandomSignSub(String app_id, String app_token, Map<String,String> params, int length){
		String sign = getRandomSign(app_id,app_token, params);
		return sign.substring(0, length);
	}
	
	//随机加密,计算签名
	public static String getRandomSign(String app_id, String app_token, Map<String,String> params){
		int al = new Random().nextInt(SignUtil.AL_TOTOAL) + 1;
		if(!DIGEST_MD5.equals(String.valueOf(al))) {
			params.put("al", String.valueOf(al));
		}
		
		return getClientSign(app_id, app_token, params);
	}

/*	//计算签名后，截取默认长度
	public static String getSignStr(String app_id, String app_token, Map<String,String> params){
		return getSignSub(app_id,app_token, params, SIGN_LENGTH);
	}
	
	//计算签名后，截取指定长度
	public static String getSignSub(String app_id, String app_token, Map<String,String> params, int length){
		String sign = getClientSign(app_id,app_token, params);
		return sign.substring(0, length);
	}*/

	//客户端:对所有参数计算签名
	public static String getClientSign(String app_id, String app_token, Map<String,String> params){
		//获取请求接口安全认证所需参数
		params.put("app_id", app_id);
		params.put("app_token", app_token);
		String nonce = String.valueOf(new Date().getTime()/1000);
		params.put("nonce", nonce);
		
		//排序
		/*Object[] key_arr = params.keySet().toArray();
		Arrays.sort(key_arr);
		//组装参数字符串
		*/
		
		Map<String,String> treeMap = new TreeMap<String,String>(params);
		StringBuffer sb = new StringBuffer("");
		for (Entry<String, String> entry : treeMap.entrySet()) {
		    String key = entry.getKey();
		    String value = entry.getValue();
		    
		    if(value==null){
		    	value = "";
		    }
		    sb.append(key).append("=").append(value).append("&");
		}
		
		/*Map<String,String> treeMap = new TreeMap<String,String>(new Comparator<String>(){
		   public int compare(String obj1,String obj2){
		    //降序排序
		    return obj2.compareTo(obj1);
		   }
		  });*/
		
		String paramString = sb.substring(0, sb.length()-1);
		
		System.out.println("----------------->client signStr: "+paramString);
		//加密,计算签名
		String sign = "";
		String al = params.get("al");
		if(al!=null && !"".equals(al)){
			if(al.equals(DIGEST_SHA1)){
				sign = StringUtil.sha_1(paramString);
			} else if(al.equals(DIGEST_SHA256)) {
				sign = StringUtil.sha_256(paramString);
			} else if(al.equals(DIGEST_SHA512)) {
				sign = StringUtil.sha_512(paramString);
			} else {
				sign = StringUtil.md5(paramString);
			}
		} else {
			sign = StringUtil.md5(paramString);
		}
		
		params.remove("app_token");
		params.put("sign", sign);
		return sign;
	}

	
	/*----------------------随机加密,只对指定参数加密----------*/
	
    //随机加密,只对指定参数加密,截取默认长度
	public static String getRandomSignByKeys(String app_id, String app_token, String paramKyes, Map<String,String> params){
		return getRandomSignSub(app_id, app_token, paramKyes, params, SIGN_LENGTH);
	}
	
    //随机加密,只对指定参数加密,并截取指定长度
	public static String getRandomSignSub(String app_id, String app_token, String paramKyes, Map<String,String> params, int length){
		String sign = getRandomSign(app_id, app_token, paramKyes, params);
		return sign.substring(0, length);
	}

	//随机加密,只对指定参数加密
	public static String getRandomSign(String app_id, String app_token, String paramKyes, Map<String,String> params){
		int al = new Random().nextInt(SignUtil.AL_TOTOAL) + 1;
		if(!DIGEST_MD5.equals(String.valueOf(al))) {
			params.put("al", String.valueOf(al));
		}
		
		return getSign(app_id, app_token, paramKyes, params);
	}

	
	//客户端：只对指定参数计算签名
	public static String getSign(String app_id, String app_token, String paramKyes, Map<String,String> params){
		//获取请求接口安全认证所需参数
		params.put("app_id", app_id);
		params.put("app_token", app_token);
		String nonce = String.valueOf(new Date().getTime()/1000);
		params.put("nonce", nonce);
		
		/**
		//从配置文件中读取约定参数
		String securityParams = AppConfig.get(securityParamKey);//安全认证所需参数
		String paramNames = AppConfig.get(paramsKey);//约定参数
		String[] paramNameArr = (paramNames+","+securityParams).split(",");*/
		
		//排序
		/*Object[] key_arr = params.keySet().toArray();
		Arrays.sort(key_arr);
		//组装参数字符串
		StringBuffer sb = new StringBuffer("");
		for(String key : key_arr){
			String value = params.get(key);
			if(StringUtils.isNotBlank(value)){
				sb.append(value).append("&");
			}
		}*/
		
		/*Map<String,String> treeMap = new TreeMap<String,String>(params);
		StringBuffer sb = new StringBuffer("");
		for (Entry<String, String> entry : treeMap.entrySet()) {
		    String key = entry.getKey();
		    String value = entry.getValue();
//		    value = StringUtils.isBlank(value)?"":value;
		    
		    if(value!=null){//只对非空参数计算签名
		    	sb.append(key).append("=").append(value).append("&");
		    }
		}*/
		
//		String securityParams = AppConfig.get("sys.request.security.params");//安全认证所需参数
		String[] paramNameArr = (paramKyes+","+securityParams).split(",");
		
		//严格按字母表顺序排序，也就是忽略大小写排序
		Arrays.sort(paramNameArr, String.CASE_INSENSITIVE_ORDER);
		
		//组装参数字符串
		StringBuffer sb = new StringBuffer("");
		for(String name : paramNameArr){
			if(name.equals("app_token")){
				sb.append(name).append("=").append(app_token).append("&");
			} else {
				String value = params.get(name);

				if(isNotBlank(value)){//只对非空参数计算签名
					sb.append(name).append("=").append(value).append("&");
				}
			}
		}
		
		/*Map<String,String> treeMap = new TreeMap<String,String>(new Comparator<String>(){
		   public int compare(String obj1,String obj2){
		    //降序排序
		    return obj2.compareTo(obj1);
		   }
		  });*/
		
		String paramString = sb.substring(0, sb.length()-1);
		
		System.out.println("----------------->client sign: "+paramString);
		//加密,计算签名
		String sign = "";
		String al = params.get("al");
		if(al!=null && !"".equals(al)){
			if(al.equals(DIGEST_SHA1)){
				sign = StringUtil.sha_1(paramString);
			} else if(al.equals(DIGEST_SHA256)) {
				sign = StringUtil.sha_256(paramString);
			} else if(al.equals(DIGEST_SHA512)) {
				sign = StringUtil.sha_512(paramString);
			} else {
				sign = StringUtil.md5(paramString);
			}
		} else {
			sign = StringUtil.md5(paramString);
		}
		
		params.remove("app_token");
		params.put("sign", sign);
		return sign;
	}
	/**======================= 客户端生成签名  end =======================================*/
	
	/**======================= 服务端验证签名 begin  ====================================*/

	public static String securityParams = "al,app_id,nonce";
	
	/**
	 * 服务端验证签名
	 * @param interfaceParams 接口定义的参数，格式：param1,param2
	 * @param securityParams 安全认证的参数,格式：param1,param2
	 * @param app_token 分配给app的token
	 * @param request 
	 * @param signature_expiry 超时时间,单位：秒 
	 * @return
	 * @throws Exception
	 */
	public static boolean checkSign(String interfaceParams, String app_token, HttpServletRequest request, long signature_expiry) throws Exception{
		String isUseSign = AppConfig.get("sys.api.sign.switch");
		if(isUseSign!=null && "OFF".equalsIgnoreCase(isUseSign)){
			return true;
		}
		
		//检查请求是否超时
		String app_id = request.getParameter("app_id");
		String nonce = request.getParameter("nonce");
		String sign = request.getParameter("sign");
		
		if(isBlank(app_id) || isBlank(nonce) || isBlank(sign)){
			return false;//非法请求
		}
		
		if(!checkLong(nonce) || sign.length()!=16) {
			return false;//非法请求
		}
		
		Long cur_time = new Date().getTime()/1000;
		Long query_time = Long.valueOf(request.getParameter("nonce"));
//		Long signature_expiry = Long.valueOf(AppConfig.get("sys.request.security.expiry"));//单位：秒
		if(cur_time-query_time > signature_expiry) {
			return false;//请求超时
		}
		
		// 根据app_id从数据库查找app_token
		/*Map<String,Object> app = appService.findById(Long.valueOf(app_id));
		if(app==null || app.size()==0){
			return false;
		}
		String app_token = (String)app.get("app_token");*/
		
		String server_sign = getServerSign(interfaceParams, securityParams, app_token, request);
		
		//比对签名
		if(!server_sign.equals(sign)){
			return false;//签名不一致
		}
		return true;
	}
	
	private static String getServerSign(String interfaceParams, String securityParams, String app_token, HttpServletRequest request){
		return getServerSignSub(interfaceParams, securityParams, app_token, request, SIGN_LENGTH);
	}
	
	private static String getServerSignSub(String interfaceParams, String securityParams, String app_token, HttpServletRequest request, int length){
		String sign = getSign(interfaceParams, securityParams,  app_token, request);
		return sign.substring(0, length);
	}
	
	
	//服务端计算签名
	private static String getSign(String interfaceParams, String app_token, HttpServletRequest request){
//		String securityParams = AppConfig.get("sys.request.security.params");
		return getSign(interfaceParams, securityParams, app_token, request);
	}
	
	//服务端计算签名
	private static String getSign(String interfaceParams, String securityParams, String app_token, HttpServletRequest request){
		String al = request.getParameter("al");
		
		if(isNotBlank(al)){
			securityParams = securityParams+","+"al";
		}
		
		String[] paramKeyArr = (interfaceParams+","+securityParams).split(",");
		
		Map<String,String> paramMap = new HashMap<String,String>();
		for(String key : paramKeyArr){
			paramMap.put(key, request.getParameter(key));
		}
		paramMap.put("app_token", app_token);
		return getSign(paramMap);
		
		//严格按字母表顺序排序，也就是忽略大小写排序
//		Arrays.sort(paramKeyArr, String.CASE_INSENSITIVE_ORDER);
		
		/*//组装参数字符串
		StringBuffer sb = new StringBuffer("");
		for(String name : paramNameArr){
			if(name.equals("app_token")){
				sb.append(name).append("=").append(app_token).append("&");
			} else {
				String value = request.getParameter(name);
				if(value == null) value = "";
				
				sb.append(name).append("=").append(value).append("&");
				
				if(isNotBlank(value)){//只对非空参数计算签名
					sb.append(name).append("=").append(value).append("&");
				}
			}
		}
		
		//加密,计算签名
		String paramString = sb.substring(0, sb.length()-1);
		System.out.println("----------------->server sign: "+paramString);
		
		if(isNotBlank(al)){
			if(al.equals(SignUtil.DIGEST_SHA1)){
				return StringUtil.sha_1(paramString);
			} else if(al.equals(SignUtil.DIGEST_SHA256)) {
				return StringUtil.sha_256(paramString);
			} else if(al.equals(SignUtil.DIGEST_SHA512)) {
				return StringUtil.sha_512(paramString);
			} else {
				return StringUtil.md5(paramString);
			}
		} else {
			return StringUtil.md5(paramString);
		}*/
	}

	//服务端计算签名*/
	private static String getSign(Map<String,String> params){
		Map<String,String> treeMap = new TreeMap<String,String>(params);
		StringBuffer sb = new StringBuffer("");
		for (Entry<String, String> entry : treeMap.entrySet()) {
		    String key = entry.getKey();
		    String value = entry.getValue();
		    
		    if(value==null){
		    	value = "";
		    }
		    sb.append(key).append("=").append(value).append("&");
		}
		//加密,计算签名
		String paramString = sb.substring(0, sb.length()-1);
		System.out.println("----------------->server sign: "+paramString);
		String al = params.get("al");
		if(isNotBlank(al)){
			if(al.equals(SignUtil.DIGEST_SHA1)){
				return StringUtil.sha_1(paramString);
			} else if(al.equals(SignUtil.DIGEST_SHA256)) {
				return StringUtil.sha_256(paramString);
			} else if(al.equals(SignUtil.DIGEST_SHA512)) {
				return StringUtil.sha_512(paramString);
			} else {
				return StringUtil.md5(paramString);
			}
		} else {
			return StringUtil.md5(paramString);
		}
	}
	/**======================= 服务端验证签名  end =======================================*/
	
	private static String getTerminalSign(String interfaceParams, String securityParams, HttpServletRequest request){
		String al = request.getParameter("al");
		
		if(isNotBlank(al)){
			securityParams = securityParams+","+"al";
		}
		
		String[] paramNameArr = (interfaceParams+","+securityParams).split(",");
		
		//严格按字母表顺序排序，也就是忽略大小写排序
		Arrays.sort(paramNameArr, String.CASE_INSENSITIVE_ORDER);
		
		//组装参数字符串
		StringBuffer sb = new StringBuffer("");
		for(String name : paramNameArr){
			String value = request.getParameter(name);

			if(isNotBlank(value)){//只对非空参数计算签名
				sb.append(name).append("=").append(value).append("&");
			}
		}
		
		//加密,计算签名
		String paramString = sb.substring(0, sb.length()-1);
		System.out.println("----------------->server terminal_sign: "+paramString);
		
		if(isNotBlank(al)){
			if(al.equals(SignUtil.DIGEST_SHA1)){
				return StringUtil.sha_1(paramString);
			} else if(al.equals(SignUtil.DIGEST_SHA256)) {
				return StringUtil.sha_256(paramString);
			} else if(al.equals(SignUtil.DIGEST_SHA512)) {
				return StringUtil.sha_512(paramString);
			} else {
				return StringUtil.md5(paramString);
			}
		} else {
			return StringUtil.md5(paramString);
		}
	}
	
	public static boolean checkTerminalSign(String interfaceParams, HttpServletRequest request) {
		String sign = request.getParameter("sign");
		String securityParams = AppConfig.get("sys.terminal.request.security.params");
		String mysign = getTerminalSign(interfaceParams, securityParams, request);
		mysign = mysign.substring(0, 16);
		if(sign.equals(mysign)){
			return true;
		}
		
		return false;
	}
	/**
	 * 字符串为空校验
	 * @param str
	 * @return
	 */
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }
    
    /**
     * 验证整型参数
     * @param value
     * @return
     */
    private static boolean checkLong(String value){
//    	Pattern pattern = Pattern.compile(reg);
    	Matcher matcher = LONG_PATTERN.matcher(value);
    	if(!matcher.matches()){
    		return false;
    	}else {
    		return true;
    	}
    }
    
    /**
     * 验证参数长度
     * @param value
     * @param maxLength
     * @return
     * @throws UnsupportedEncodingException
     */
    @SuppressWarnings("unused")
    private static boolean checkLength(String value, int maxLength) throws UnsupportedEncodingException{
    	return checkLength(value, 0, maxLength);
    }
    /**
     * 验证参数长度
     * @param value
     * @param minLength
     * @param maxLength
     * @return
     * @throws UnsupportedEncodingException
     */
    private static boolean checkLength(String value, int minLength, int maxLength) throws UnsupportedEncodingException{
    	if(value.getBytes(charset).length<minLength || value.getBytes(charset).length>maxLength){
			return false;
		}else {
			return true;
		}
    }
    
	public static void main(String[] args) {

	}
}

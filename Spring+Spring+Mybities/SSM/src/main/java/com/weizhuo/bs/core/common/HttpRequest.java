package com.weizhuo.bs.core.common;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类HttpRequest.java的实现描述：
 * http 请求工具类 
 * @author
 */
public class HttpRequest {
    private static Logger logger = LoggerFactory.getLogger(HttpRequest.class);
    private static int CONN_TIMEOUT = 5 * 1000; //连接延时
    private static String ENCODE_UTF8 = "UTF-8";
    
  //application/x-javascript, text/xml->xml数据, application/json->json对象, application/x-www-form-urlencoded->表单数据
    public static final String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded";
    public static final String CONTENT_TYPE_JSON = "application/json";
    
    
	/**
	 * Post请求
	 * @param path
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static boolean sendRequest(String requestMethod, String path, Map<String, String> params) throws Exception {
	    InputStream inputStream = null;
	    if(StringUtils.isBlank(requestMethod)){
	        logger.warn("request method is not null.({})", requestMethod);
	        return false;
	    }else if( "post".equals(requestMethod.toLowerCase()) ){
	        inputStream = sendPostRequest(path, params, CONTENT_TYPE_FORM);
	    }else if( "get".equals(requestMethod.toLowerCase()) ){
//            inputStream = sendGetRequest(path, params);
        }else{
            logger.warn("request method must equal (post or get). but it's ({})", requestMethod);
            return false;
        }
		if (inputStream != null) {
		    ByteBuffer buff = parseInputStream(inputStream);
		    if(logger.isDebugEnabled()){
		        logger.debug(new String(buff.array(), ENCODE_UTF8));
		    }
			return true;
		}
		return false;
	}
	/**=========================================== [post] ================================*/
	public static String sessionId = "";  
	static boolean loginAuth = false; 	
	public static String login(String url, String username, String password) throws IOException {
		loginAuth = true;
       URL loginUrl = new URL(url);
       HttpURLConnection connection = (HttpURLConnection) loginUrl.openConnection();
 
 
       // Output to the connection. Default is
       // false, set to true because post
       // method must write something to the
       // connection
       // 设置是否向connection输出，因为这个是post请求，参数要放在
       // http正文内，因此需要设为true
       connection.setDoOutput(true);
       // Read from the connection. Default is true.
       connection.setDoInput(true);
       // Set the post method. Default is GET
       connection.setRequestMethod("POST");
       // Post cannot use caches
       // Post 请求不能使用缓存
       connection.setUseCaches(false);
 
       // This method takes effects to
       // every instances of this class.
       // URLConnection.setFollowRedirects是static函数，作用于所有的URLConnection对象。
       // connection.setFollowRedirects(true);
      
       // This methods only
       // takes effacts to this
       // instance.
       // URLConnection.setInstanceFollowRedirects是成员函数，仅作用于当前函数
       connection.setInstanceFollowRedirects(false);
      
       // Set the content type to urlencoded,
       // because we will write
       // some URL-encoded content to the
       // connection. Settings above must be set before connect!
       // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
       // 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
       // 进行编码
       connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
       // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
       // 要注意的是connection.getOutputStream会隐含的进行connect。
       connection.connect();
 
       DataOutputStream out = new DataOutputStream(connection.getOutputStream());
 
         // 要传的参数
       String content = URLEncoder.encode("username", "UTF-8") + "="+ URLEncoder.encode("superadmin", "UTF-8");
       content += "&" + URLEncoder.encode("password", "UTF-8") + "="
              + URLEncoder.encode("17c4520f6cfd1ab53d8745e84681eb49", "UTF-8");
 
       // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写道流里面
       out.writeBytes(content);
 
       out.flush();
       out.close(); // flush and close
 
          //Get Session ID
       String key = "";
       if (connection != null) {
           for (int i = 1; (key = connection.getHeaderFieldKey(i)) != null; i++) {
              if (key.equalsIgnoreCase("set-cookie")) {
                  sessionId = connection.getHeaderField(key);
                  sessionId = sessionId.substring(0, sessionId.indexOf(";"));
              }
           }
       }     
       connection.disconnect();
       return sessionId;
    }
	/**
	 * post请求(默认form提交)
	 * @param path
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public static String post(String loginUrl, String username, String password, String path, Map<String, String> params) throws IOException{
		login(loginUrl, username, password);
        return postForm(path, params);
	}
	
	/**
	 * post请求(默认form提交)
	 * @param path
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public static String post(String path, Map<String, String> params) throws IOException{
        return postForm(path, params);
	}
	
	/**
	 * post请求(form提交)
	 * @param path
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public static String postForm(String path, Map<String, String> params) throws IOException{
        return post(path, params, CONTENT_TYPE_FORM);
	}
	
	/**
	 * post请求(json提交)
	 * @param path
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public static String postJson(String path, Map<String, String> params) throws IOException{
        return post(path, params, CONTENT_TYPE_JSON);
	}
	
	/**
	 * post请求
	 * @param path
	 * @param params
	 * @param contentType request的header
	 * @return
	 * @throws IOException
	 */
	public static String post(String path, Map<String, String> params, String contentType) throws IOException{
		InputStream in = sendPostRequest(path, params, contentType);
		if(in==null){
			return null;
		}
		
        String result = getResponseResult(in, ENCODE_UTF8);
        return result;
	}
	
	/**
	 * post请求
	 * @param path
	 * @param params
	 * @param contentType request的header
	 * @return
	 * @throws IOException
	 */
	public static InputStream sendPostRequest(String path, Map<String, String> params, String contentType) throws IOException{
	    //拼装请求参数
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
        	String value = entry.getValue()==null?"":entry.getValue();
            sb.append(entry.getKey()).append('=').append(URLEncoder.encode(value, ENCODE_UTF8)).append('&');
        }
        sb.deleteCharAt(sb.length() - 1);
        
        byte[] entityData = sb.toString().getBytes();
        logger.debug("http post send, url({}), params({})", path, sb);
        return sendPostRequest(path, entityData, contentType);
	}
	
	/**
	 * post请求
	 * @param path
	 * @param body 请求的参数体
	 * @param contentType request的header
	 * @return
	 * @throws IOException
	 */
	public static InputStream sendPostRequest(String path, byte[] body, String contentType) throws IOException{
	    URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        if(loginAuth){
        	conn.setRequestProperty("Cookie", sessionId);
        }
        
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", contentType);
        conn.setRequestProperty("Content-Length", String.valueOf(body.length));
        conn.setConnectTimeout(CONN_TIMEOUT);
        
        OutputStream outputStream = conn.getOutputStream();
        outputStream.write(body);
        outputStream.flush();
        outputStream.close();
        
        logger.debug("http response code ({})...", conn.getResponseCode());
        if (conn.getResponseCode() == 200) {
            return conn.getInputStream();
        }else{
            ByteBuffer buff = parseInputStream(conn.getErrorStream());
            logger.error("http request error. {}", new String(buff.array(), ENCODE_UTF8));
        }
        return null;
	}
	
	/*=========================== [GET] ================================*/
	/**
     * 客户端用Get方法向服务器提交请求,并获取服务器响应信息
     * @param path
     * @param params
     */
    public static String get(String path, Map<String, String> params) {
        return get(path, params, "UTF-8");
    }
	
	/**
     * 客户端用Get方法向服务器提交请求,并获取服务器响应信息
     * @param path
     * @param params
     */
    public static String get(String path, Map<String, String> params, String charset) {
        try {
            StringBuilder sb = new StringBuilder();
            if(params != null && params.size() > 0){
                for (Map.Entry<String, String> entry : params.entrySet()) {
                	String value = entry.getValue()==null?"":entry.getValue();
                    sb.append(entry.getKey()).append('=').append(URLEncoder.encode(value, ENCODE_UTF8)).append('&');
                }
                sb.deleteCharAt(sb.length() - 1);
            }
            
            URL url = (path.indexOf("?") > -1) ? new URL(path + "&" + sb.toString())
            						: new URL(path + "?" + sb.toString());
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
            // 默认情况下是false;
            httpUrlConn.setDoOutput(false);
            // 设置是否从httpUrlConnection读入，默认情况下是true
            httpUrlConn.setDoInput(true);
            // Get 请求不能使用缓存
            httpUrlConn.setUseCaches(false);
            // 设定请求的方法为"GET"，默认是GET
            httpUrlConn.setRequestMethod("GET");
            String result = getResponseResult(httpUrlConn.getInputStream(), charset);
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
	
	/**
     * 客户端用Get方法向服务器提交请求,并获取服务器响应信息
     * @param path
     * @param params
     */
    public static ByteBuffer sendGetRequest(String path, Map<String, String> params) {
        try {
            StringBuilder sb = new StringBuilder();
            if(params != null && params.size() > 0){
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    sb.append(entry.getKey()).append('=').append(
                              URLEncoder.encode(entry.getValue(), ENCODE_UTF8)).append('&');
                }
                sb.deleteCharAt(sb.length() - 1);
            }
            URL url = (path.indexOf("?") > -1) ? new URL(path + "&" + sb.toString())
            						: new URL(path + "?" + sb.toString());
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
            // 默认情况下是false;
            httpUrlConn.setDoOutput(false);
            // 设置是否从httpUrlConnection读入，默认情况下是true
            httpUrlConn.setDoInput(true);
            // Get 请求不能使用缓存
            httpUrlConn.setUseCaches(false);
            // 设定请求的方法为"GET"，默认是GET
            httpUrlConn.setRequestMethod("GET");
            return parseInputStream(httpUrlConn.getInputStream());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
    

    
    /*======================================================================*/
	/**
	 * 将inputStream 读取 成 ByteBuffer。
	 * @param inputStream
	 * @throws IOException 
	 */
	public static ByteBuffer parseInputStream(InputStream inputStream) throws IOException{
	    DataInputStream inStream = new DataInputStream(inputStream);
	    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] byts = new byte[1024];
        int len = 0;
        while ( (len = inStream.read(byts)) >= 0 ) {
        	outStream.write(byts, 0, len);
        }
        inStream.close();
        return ByteBuffer.wrap(outStream.toByteArray());
	}
    
    /**
     * 从输入流中读入数据
     * @param in
     */
    public static String readBuffer(InputStreamReader in) {
        BufferedReader reader = new BufferedReader(in);
        try {
            String line = "";
            StringBuffer sb = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return StringUtils.trimToEmpty(sb.toString());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }finally{
            try {
                reader.close();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return "";
    }

    /**
     * 按制定编码读取返回结果
     * @param inputStream
     * @param charset
     * @return
     * @throws IOException
     */
    public static String getResponseResult(InputStream inputStream, String charset) throws IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream, charset));
        String line;
        String returnMessage = "";
        while ((line = rd.readLine()) != null) {
        	returnMessage += line;
        }
//        System.out.println("===============================");
//        System.out.println("ret_str:" + returnMessage);
//        System.out.println("===============================");
        rd.close();
		
		return returnMessage;
	}
}

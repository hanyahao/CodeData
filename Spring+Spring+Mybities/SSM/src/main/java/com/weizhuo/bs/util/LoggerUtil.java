package com.weizhuo.bs.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

public class LoggerUtil {
	private final static Logger logger = Logger.getLogger(LoggerUtil.class);
	@SuppressWarnings("unused")
	private static String _timezone = "+0800" ;
	private static String IP = null;
	public static String platformCode = "S400";
	static {
		try {
			_timezone = new SimpleDateFormat("ZZZZ").format(new Date());
			
			IP = InetAddress.getLocalHost().getHostAddress();
			MDC.put("IP", IP);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
   /*
    public static Logger getLoggerByName(String name) {
        // 生成新的Logger
        // 如果已经有了一個Logger实例返回现有的
        Logger logger = Logger.getLogger(name);
        // 清空Appender。特別是不想使用现存实例时一定要初期化
        logger.removeAllAppenders();
        // 设定Logger级别。
        logger.setLevel(Level.DEBUG);
        // 设定是否继承父Logger。
        // 默认为true。继承root输出。
        // 设定false後将不输出root。
        logger.setAdditivity(true);
        // 生成新的Appender
        FileAppender appender = new RollingFileAppender();
        PatternLayout layout = new PatternLayout();
        // log的输出形式
        String conversionPattern = "[%d] %p %t %c - %m%n";
        layout.setConversionPattern(conversionPattern);
        appender.setLayout(layout);
        // log输出路径
        // 这里使用了环境变量[catalina.home]，只有在tomcat环境下才可以取到
        String tomcatPath = java.lang.System.getProperty("catalina.home");
        appender.setFile(tomcatPath + "/logs/" + name + ".log");
        // log的文字码
        appender.setEncoding("UTF-8");
        // true:在已存在log文件后面追加 false:新log覆盖以前的log
        appender.setAppend(true);
        // 适用当前配置
        appender.activateOptions();
        // 将新的Appender加到Logger中
        logger.addAppender(appender);
        return logger;
    }
    */
    /*
    public static String getNewMsg(String interfaceNoString, String moduleName, String serviceName, String params, String oldMsg){
    	if (params == null){
    		params = StringUtils.EMPTY;
    	}
    	if (serviceName == null){
    		serviceName = StringUtils.EMPTY;
    	}
    	
    	StringBuffer sb = new StringBuffer("");
    	sb.append("<").append(_timezone).append("> -- [")
    	.append(interfaceNoString).append("] -- [")
    	.append(Config.platformkey).append("] -- [")
    	.append(IP).append("] -- [")
    	.append(moduleName).append("] -- <")
    	.append(serviceName).append("> -- <")
    	.append(params).append("> -- [")
    	.append(oldMsg).append("]");
    	return sb.toString();
    }*/
    

    public static String getNewMsg(String interfaceNoString, String moduleName, String serviceName, String params, String oldMsg){
    	return oldMsg;
    }
	
    public static void main(String[] args) {
		logger.info(getNewMsg("10101010","action ","login","username:123","用户登录"));
	}
}


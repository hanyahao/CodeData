package com.weizhuo.bs.core.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
	public static Properties confProperties;

	static {
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void init() throws Exception{
		if(confProperties == null){
	    	confProperties = new Properties();
	    }

		InputStream in = AppConfig.class.getClassLoader().getResourceAsStream("properties/app-config.properties");
		try {
			confProperties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			in.close();
		}
	}
	
	public static Properties getProperties() throws Exception{
		init();
		return confProperties;
	}
	
	public static void clear(){
		confProperties.clear();
		confProperties = null;
	}
	
	public static String get(String key) {
		if(confProperties == null){
			try {
				init();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return confProperties.getProperty(key);
	}
}

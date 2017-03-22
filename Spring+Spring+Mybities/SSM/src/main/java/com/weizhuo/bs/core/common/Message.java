package com.weizhuo.bs.core.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Message {
	public static Properties confProperties;

	static {
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void init() throws Exception{
		/*File rootDirectory = new File(Message.class.getClassLoader().getResource("").getPath());
	    File[] propertiesFiles = rootDirectory.listFiles(new FileNameSelector("properties"));
		
	    if(confProperties == null){
	    	confProperties = new Properties();
	    }
		for(File file : propertiesFiles){
			FileInputStream fis = new FileInputStream(file);
			confProperties.load(fis);
		}*/
		if(confProperties == null){
	    	confProperties = new Properties();
	    }

/*		InputStream in = ClassLoader.getSystemResourceAsStream("properties/message.properties");
		if(in == null){
			in = ClassLoader.getSystemResourceAsStream("resources/properties/message.properties");
		}*/

		InputStream in = Message.class.getClassLoader().getResourceAsStream("properties/message.properties");
		
		/*String path = Message.class.getClassLoader().getResource("db.properties").getPath();
		FileInputStream in = new FileInputStream(path);*/
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

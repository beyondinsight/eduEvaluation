package com.philip.edu.eval.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

 

public class PropertiesUtil {
   
	 
	public static Properties getProperty(String pp) {
		InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(pp+".properties");
		Properties p = new Properties();
		try {
			p.load(inputStream);
			inputStream.close();
		} catch (IOException e) {
			//读取配置文件出错
			e.printStackTrace();
		}
		return p;
	}
	
  
} 

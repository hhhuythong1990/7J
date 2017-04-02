package com.sevenj.web.util;

import java.io.InputStream;
import java.util.Properties;


public class Path {
	
	
	public static String PATH_FOLDER;
	
	public  Path(){
		Properties m_Properties;
		try{
			
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			InputStream oInputStream = loader.getResourceAsStream ("./application.properties");
			 m_Properties  = new Properties();
			 m_Properties.load(oInputStream);
			 
			 Path.PATH_FOLDER = m_Properties.getProperty("PATH_FOLDER");
			 
		}catch (Exception e) {
			e.printStackTrace();
		}
	}	
}

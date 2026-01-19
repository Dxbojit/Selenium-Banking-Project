package com.ibm.framework.config;

import java.io.FileInputStream;
import java.util.Properties;

import com.ibm.framework.constants.FrameworkConstants;

public final class ConfigReader {
	private static Properties prop;
	
	private ConfigReader() {
	}
	
	static {
		try {
			FileInputStream fin = new FileInputStream(FrameworkConstants.CONFIG_PATH);
			prop = new Properties();
			prop.load(fin);
		}
		catch(Exception e) {
			throw new RuntimeException("Failed to load config.properties");
		}
	}
	
	public static String get(String key) {
		 String value = prop.getProperty(key);
		 if (value == null) {
	            throw new RuntimeException("Property not found in config.properties: " + key);
	        }
		return value;
	}


}

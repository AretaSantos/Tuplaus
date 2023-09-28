package com.tuplaus;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ReadPropFile {
	
	public static Properties getProperties(String filePath) {
		
		Properties properties = new Properties();
		InputStream input = null;

		try {
			
			input = new FileInputStream(filePath);
			properties.load(input);
			
		} catch (IOException ex) {
			ex.printStackTrace();
			
		} finally {
			
			if (input != null) {
				
				try {
					
					input.close();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}	
		
		return properties;
	}
}
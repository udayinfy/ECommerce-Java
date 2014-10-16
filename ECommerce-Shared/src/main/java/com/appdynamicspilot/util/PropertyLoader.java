package com.appdynamicspilot.util;

import java.io.InputStream;
import java.util.Properties;


public class PropertyLoader {

	private static Properties p=null;
	static
	{
		try
		{
			p = new Properties();
			InputStream is=PropertyLoader.class.getClassLoader().getResourceAsStream("backend_config.properties");
			if(is==null){
				is=PropertyLoader.class.getClassLoader().getResourceAsStream("/backend_config.properties");
			}
			p.load(is);
			is.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static String getProperty(String key)
	{
		return p.getProperty(key);
	}
}

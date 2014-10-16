package com.appdynamicspilot.util;

import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ServiceRegistry {

	private static ApplicationContext context = null;
	public static final String CONENZA_PERSISTENCE ="dbProvider";

	static{
		//String[] file = {"../applicationContext-jdbc.xml", "../applicationContext-service.xml", "../applicationContext-resources.xml"}; 
		String[] file = {"D:/myTomcat/tomcat/webapps/appdynamicspilot/WEB-INF/applicationContext.xml","D:/myTomcat/tomcat/webapps/appdynamicspilot/WEB-INF/applicationContext-jms.xml"};
		initialize(file);
	}
	
	public static Object getBean(final String key) {
		//String[] file = {"../applicationContext-jdbc.xml", "../applicationContext-service.xml", "../applicationContext-resources.xml"}; 
		//String[] file = {"applicationContext-jdbc.xml", "applicationContext-service.xml", "applicationContext-resources.xml"};
		//initialize(file);
		return get(key);
	}
	
	public static void initialize(final String[] file) {
		if (context == null) {
			context = new FileSystemXmlApplicationContext(file);
		}
	}

//	public static void initialize() {
//		if (context == null) {
//			context = SpringContextSingleton.getInstance().getContext();
//		}
//	}

	public static Object get(final String key) {
		return context.getBean(key);
	}

	public static String getMessage(final String key, final Locale locale) {
		return context.getMessage(key, new Object[0], locale);
	}
	public static String getMessage(final String key,final Object[] args, final Locale locale) {
		return context.getMessage(key, args, locale);
	}
}

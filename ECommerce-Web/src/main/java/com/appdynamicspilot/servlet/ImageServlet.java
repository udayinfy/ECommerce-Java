package com.appdynamicspilot.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageServlet extends HttpServlet {
	private static final int MILLIS = 1000;
	private ServletContext context = null;
	
	public void init(ServletConfig config) {
		context = config.getServletContext();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Random rand = new Random(); 
		int pickedNumber = rand.nextInt(10);
		
		try {
			Thread.currentThread().sleep(pickedNumber * MILLIS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		InputStream in = context.getResourceAsStream("/images/facebook.png");	
		response.setContentType("image/png");
		OutputStream out = response.getOutputStream();
		byte [] buf = new byte[1024];
		int i = 0;
		while ((i= in.read(buf)) != -1) {
			out.write(buf, 0, i);
		}
		
	}
}

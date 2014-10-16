package com.appdynamics.servlet;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * Servlet implementation class for Servlet: NotifyServlet
 *
 */
 
 public class NotifyServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	public NotifyServlet() {
		super();
	}   	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mailId = null;
		String subject = null;
		String content = null;
		System.out.println("Do Get of NotifyServlet called");
		try{
			
			mailId = request.getParameter("emailId");
			subject = request.getParameter("subject");
			content = request.getParameter("content");
			SimpleSendEmail email = new SimpleSendEmail();
			email.sendMail(mailId, subject,content);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		response.getWriter().println("Your message has been successfully sent to " + mailId);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Do Post called");
		doGet(request,response);
	}   	  	    
}



 class SimpleSendEmail {
	public void sendMail(String mailId, String subject, String content) {
		
		Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
 
			Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("appdynamics.neev","neev123appy");
					}
				});
		
			try {
	 
				Message message = new MimeMessage(session);
				
				message.setFrom(new InternetAddress("appdynamics.neev@gmail.com"));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(mailId));
				message.setSubject(subject);
				message.setText(content);
	 
				Transport.send(message);
				
				System.out.println("Done");
	 
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
	}
}
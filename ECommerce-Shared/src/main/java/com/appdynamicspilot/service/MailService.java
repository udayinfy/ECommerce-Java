package com.appdynamicspilot.service;

import org.apache.log4j.Logger;


public class MailService {
	
	private MailSender mailerService;
	private static final Logger log = Logger.getLogger(MailService.class);

	public MailSender getMailerService() {
		return mailerService;
	}

	public void setMailerService(MailSender mailerService) {
		this.mailerService = mailerService;
	}


	public void sendOrderMail(String email, String orderId) {
		log.info("SENDING MAIL TO :"+email);
		String htmlBody = "<html><head><title></title></head><body> <b>Thank you for placing your order(s) with appdynamics. The following are the orders that have been placed by you:  </b> <br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;"+orderId+"<br/> <br/><br/><br/>Thanks,<br/> AppDynamics";
		String subject = "[Appdynamics] Attention! Order Confirmation";
		mailerService.sendemail(email, htmlBody, "Your transaction id "+orderId, subject);
	}
}

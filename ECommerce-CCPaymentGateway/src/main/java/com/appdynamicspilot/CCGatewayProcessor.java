package com.appdynamicspilot;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by shiv.loka on 7/7/2015.
 */

@Path("/webpay")
public class CCGatewayProcessor {
	
	private long ccNumber, orderId;
	private String username;
	private boolean returnFailure;
	private static final Logger logger = LoggerFactory
			.getLogger(CCGatewayProcessor.class);


	@GET
	@Path("/cc-pay/{ccNumber}/{userName}/{orderId}/{fail}")
	@Produces(MediaType.APPLICATION_JSON)
	public String processPayment(@PathParam("userName") String username,
			@PathParam("ccNumber") long ccNumber,@PathParam("orderId") long orderId, @PathParam("fail") boolean returnFailure){
		this.ccNumber = ccNumber;
		this.returnFailure = returnFailure;
		this.username = username;
		this.orderId = orderId;
		
		logger.info("credit card number is:" +this.ccNumber);
		logger.info("Order : " + this.orderId + " has been processed for the user: " +this.username);

		if(this.returnFailure){
			
			return "Sorry we failed to process the order " +returnFailure;

		}
		return "Order : " + this.orderId + " has been processed for the user: " +this.username;

	}
}

package com.appdynamicspilot.exception;

public class OrderException extends Exception{

	private String message;
	private Throwable t;
	
	public OrderException(String message,Throwable t){
		this.message=message;
		this.t=t;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Throwable getT() {
		return t;
	}

	public void setT(Throwable t) {
		this.t = t;
	}
	
	
	
}

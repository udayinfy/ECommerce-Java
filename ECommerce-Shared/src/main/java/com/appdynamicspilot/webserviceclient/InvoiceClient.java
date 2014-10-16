package com.appdynamicspilot.webserviceclient;

import java.io.IOException;

import java.net.URL;

import org.apache.axis.client.Service;
import org.apache.axis.client.Call;

import org.apache.axis.client.*;
import org.apache.axis.encoding.XMLType;
import org.apache.axis.utils.Options;
import org.tempuri.OrderDetail;

import javax.xml.rpc.ParameterMode;
import java.util.logging.*; 
import java.util.ArrayList;


import javax.xml.namespace.QName;


 public class InvoiceClient {
	Logger logger=Logger.getLogger("Invoice");
	
	public String receiveInvoice(ArrayList<OrderDetail> orderDetailList) throws Exception {
		try {
				FileHandler handler = new FileHandler("my.log", true);	 
					 
			   logger.setLevel(Level.ALL); 
			   logger.addHandler(handler);    
			   Thread.sleep(10);
			   
			   
			   System.out.println("==========Before Calling .net Webservice=============");
				
				URL url = new URL("http://ec2-50-17-161-205.compute-1.amazonaws.com:8000/invoice/Invoice_Service.asmx?wsdl");

				Service service = new Service();

				Call call  = (Call)service.createCall();
				call.setTargetEndpointAddress(url);
				String result = (String)call.invoke("DoWork", new Object[]{""});
				System.out.println("==========.net Webservice successfully called==============");
				
				System.out.println(result);   

				return result;      
			
			  /*
			   
			   	  
			   String endpoint = "http://192.168.1.8:8088/Invoice_Service.asmx?wsdl";
			   System.out.println("EndPoint : "+endpoint);
			  
			   Service  service = new Service();
			   Call     call    = (Call) service.createCall();

			   call.setTargetEndpointAddress( new java.net.URL(endpoint) );
			   call.setOperationName( "GenerateInvoice" );
			   call.addParameter( "orderDetailList", XMLType.XSD_ANYTYPE, ParameterMode.IN );
			   call.setReturnType( XMLType.XSD_STRING );
					 
			   System.out.println("");
				*/ 
       } catch (Exception e) {
			System.err.println(e.toString());
			return null;
       }
	} 	 
	
}

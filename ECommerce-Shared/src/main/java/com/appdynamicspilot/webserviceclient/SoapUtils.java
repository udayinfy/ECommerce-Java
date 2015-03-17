/*
 * Copyright 2015 AppDynamics, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.appdynamicspilot.webserviceclient;

import java.rmi.RemoteException;

import org.apache.log4j.Logger;
import org.apahce.axis.

import com.appdynamics.inventory.OrderServicePortTypeProxy;


public class SoapUtils {
	private String axisUrl;
	private String axis14Url;
	private static final Logger log = Logger.getLogger(SoapUtils.class);
	
	public void setAxisUrl(String axisUrl) {
		this.axisUrl = axisUrl;
	}
	
	public Long raisePO(Long itemId,Integer quanity){
		com.appdynamics.inventory.xsd.OrderRequest orderRequest=new com.appdynamics.inventory.xsd.OrderRequest();
		OrderServicePortTypeProxy ospp=new OrderServicePortTypeProxy();
		ospp.setEndpoint(axisUrl);
		try {
            orderRequest.setItemId(itemId);
            orderRequest.setQuantity(new Long(quanity));
            log.debug("%%%%%%%%%%% Request time(ms) in AppdynamicsPilot SoapUtils before %%%%%%%:: " + System.currentTimeMillis());
            Long orderId = ospp.createOrder(orderRequest);
            log.debug("%%%%%%%%%%% Request time(ms) in AppdynamicsPilot SoapUtils after %%%%%%%:: " + System.currentTimeMillis());
            log.debug("Order Id " + orderId.toString());
            return orderId;
        } catch (RemoteException rmi) {
            //eat this to keep error count stable
		} catch (Exception e) {
			e.printStackTrace();
            //if you log an error it will start blowing up the error count
		} 
		return Long.valueOf(0);
	}
	/**
	 * This API is for generating error at inventory server . CartAction.sendItem will specify the wrong wsdl url here
	 * 
	 */
	public Long raisePO(Long itemId,Integer quanity,String wsdlURL){
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>> trying with >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+wsdlURL);
		com.appdynamics.inventory.xsd.OrderRequest orderRequest=new com.appdynamics.inventory.xsd.OrderRequest();
		OrderServicePortTypeProxy ospp=new OrderServicePortTypeProxy();
		ospp.setEndpoint(wsdlURL);
		try {
			orderRequest.setItemId(itemId);
			orderRequest.setQuantity(new Long(quanity));
			log.debug("%%%%%%%%%%% Request time(ms) in AppdynamicsPilot SoapUtils before %%%%%%%:: " + System.currentTimeMillis());
			Long orderId=ospp.createOrder(orderRequest);
			log.debug("%%%%%%%%%%% Request time(ms) in AppdynamicsPilot SoapUtils after %%%%%%%:: " + System.currentTimeMillis());
			log.debug("Order Id "+orderId.toString());
			return orderId;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return Long.valueOf(0);
	}
	public Long raiseJMSPO(){
		com.appdynamics.inventory.xsd.OrderRequest orderRequest=new com.appdynamics.inventory.xsd.OrderRequest();
		OrderServicePortTypeProxy ospp=new OrderServicePortTypeProxy();
		ospp.setEndpoint(axisUrl);
		try {
			orderRequest.setItemId(5L);
			orderRequest.setQuantity(1L);
			Long orderId=ospp.createOrder(orderRequest);
			log.debug("Order Id "+orderId.toString());
			return orderId;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return Long.valueOf(0);
	}

	public String getAxisUrl() {
	    return axisUrl;
	}

	public String getAxis14Url() {
	    return axis14Url;
	}

	public void setAxis14Url(String axis14Url) {
	    this.axis14Url = axis14Url;
	}
			
	
}

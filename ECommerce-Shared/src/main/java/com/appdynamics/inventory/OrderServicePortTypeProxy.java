package com.appdynamics.inventory;

import java.rmi.RemoteException;

import com.appdynamics.inventory.xsd.OrderRequest;

public class OrderServicePortTypeProxy implements com.appdynamics.inventory.OrderServicePortType {
  private String _endpoint = null;
  private com.appdynamics.inventory.OrderServicePortType orderServicePortType = null;
  
  public OrderServicePortTypeProxy() {
    _initOrderServicePortTypeProxy();
  }
  
  public OrderServicePortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initOrderServicePortTypeProxy();
  }
  
  private void _initOrderServicePortTypeProxy() {
    try {
      orderServicePortType = (new com.appdynamics.inventory.OrderServiceLocator()).getOrderServiceSOAP11port_http();
      if (orderServicePortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)orderServicePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)orderServicePortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (orderServicePortType != null)
      ((javax.xml.rpc.Stub)orderServicePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.appdynamics.inventory.OrderServicePortType getOrderServicePortType() {
    if (orderServicePortType == null)
      _initOrderServicePortTypeProxy();
    return orderServicePortType;
  }
  
  public long createOrder(com.appdynamics.inventory.xsd.OrderRequest orderRequest) throws java.rmi.RemoteException{
    if (orderServicePortType == null)
      _initOrderServicePortTypeProxy();
    return orderServicePortType.createOrder(orderRequest);
  }
  
  public static void main(String[] args) {
	OrderServicePortTypeProxy ospp=new OrderServicePortTypeProxy();
	com.appdynamics.inventory.xsd.OrderRequest orderRequest=new com.appdynamics.inventory.xsd.OrderRequest();
	try {
		orderRequest.setItemId(2l);
		orderRequest.setQuantity(1l);
		Long orderId=ospp.createOrder(orderRequest);
		System.out.println("Order Id "+orderId.toString());
		
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
  
}
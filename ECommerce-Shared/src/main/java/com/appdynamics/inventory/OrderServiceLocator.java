/**
 * OrderServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.appdynamics.inventory;

public class OrderServiceLocator extends org.apache.axis.client.Service implements com.appdynamics.inventory.OrderService {

    public OrderServiceLocator() {
    }


    public OrderServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public OrderServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for OrderServiceSOAP11port_http
    private java.lang.String OrderServiceSOAP11port_http_address = "http://192.168.1.182:8080/cart/services/OrderService";

    public java.lang.String getOrderServiceSOAP11port_httpAddress() {
        return OrderServiceSOAP11port_http_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String OrderServiceSOAP11port_httpWSDDServiceName = "OrderServiceSOAP11port_http";

    public java.lang.String getOrderServiceSOAP11port_httpWSDDServiceName() {
        return OrderServiceSOAP11port_httpWSDDServiceName;
    }

    public void setOrderServiceSOAP11port_httpWSDDServiceName(java.lang.String name) {
        OrderServiceSOAP11port_httpWSDDServiceName = name;
    }

    public com.appdynamics.inventory.OrderServicePortType getOrderServiceSOAP11port_http() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(OrderServiceSOAP11port_http_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getOrderServiceSOAP11port_http(endpoint);
    }

    public com.appdynamics.inventory.OrderServicePortType getOrderServiceSOAP11port_http(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.appdynamics.inventory.OrderServiceSOAP11BindingStub _stub = new com.appdynamics.inventory.OrderServiceSOAP11BindingStub(portAddress, this);
            _stub.setPortName(getOrderServiceSOAP11port_httpWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setOrderServiceSOAP11port_httpEndpointAddress(java.lang.String address) {
        OrderServiceSOAP11port_http_address = address;
    }


    // Use to get a proxy class for OrderServiceSOAP12port_http
    private java.lang.String OrderServiceSOAP12port_http_address = "http://192.168.1.182:8080/cart/services/OrderService";

    public java.lang.String getOrderServiceSOAP12port_httpAddress() {
        return OrderServiceSOAP12port_http_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String OrderServiceSOAP12port_httpWSDDServiceName = "OrderServiceSOAP12port_http";

    public java.lang.String getOrderServiceSOAP12port_httpWSDDServiceName() {
        return OrderServiceSOAP12port_httpWSDDServiceName;
    }

    public void setOrderServiceSOAP12port_httpWSDDServiceName(java.lang.String name) {
        OrderServiceSOAP12port_httpWSDDServiceName = name;
    }

    public com.appdynamics.inventory.OrderServicePortType getOrderServiceSOAP12port_http() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(OrderServiceSOAP12port_http_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getOrderServiceSOAP12port_http(endpoint);
    }

    public com.appdynamics.inventory.OrderServicePortType getOrderServiceSOAP12port_http(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.appdynamics.inventory.OrderServiceSOAP12BindingStub _stub = new com.appdynamics.inventory.OrderServiceSOAP12BindingStub(portAddress, this);
            _stub.setPortName(getOrderServiceSOAP12port_httpWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setOrderServiceSOAP12port_httpEndpointAddress(java.lang.String address) {
        OrderServiceSOAP12port_http_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     * This service has multiple ports for a given interface;
     * the proxy implementation returned may be indeterminate.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.appdynamics.inventory.OrderServicePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.appdynamics.inventory.OrderServiceSOAP11BindingStub _stub = new com.appdynamics.inventory.OrderServiceSOAP11BindingStub(new java.net.URL(OrderServiceSOAP11port_http_address), this);
                _stub.setPortName(getOrderServiceSOAP11port_httpWSDDServiceName());
                return _stub;
            }
            if (com.appdynamics.inventory.OrderServicePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.appdynamics.inventory.OrderServiceSOAP12BindingStub _stub = new com.appdynamics.inventory.OrderServiceSOAP12BindingStub(new java.net.URL(OrderServiceSOAP12port_http_address), this);
                _stub.setPortName(getOrderServiceSOAP12port_httpWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("OrderServiceSOAP11port_http".equals(inputPortName)) {
            return getOrderServiceSOAP11port_http();
        }
        else if ("OrderServiceSOAP12port_http".equals(inputPortName)) {
            return getOrderServiceSOAP12port_http();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://inventory.appdynamics.com", "OrderService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://inventory.appdynamics.com", "OrderServiceSOAP11port_http"));
            ports.add(new javax.xml.namespace.QName("http://inventory.appdynamics.com", "OrderServiceSOAP12port_http"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("OrderServiceSOAP11port_http".equals(portName)) {
            setOrderServiceSOAP11port_httpEndpointAddress(address);
        }
        else 
if ("OrderServiceSOAP12port_http".equals(portName)) {
            setOrderServiceSOAP12port_httpEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}

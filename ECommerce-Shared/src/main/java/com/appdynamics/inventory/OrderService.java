/**
 * OrderService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.appdynamics.inventory;

public interface OrderService extends javax.xml.rpc.Service {
    public java.lang.String getOrderServiceSOAP11port_httpAddress();

    public com.appdynamics.inventory.OrderServicePortType getOrderServiceSOAP11port_http() throws javax.xml.rpc.ServiceException;

    public com.appdynamics.inventory.OrderServicePortType getOrderServiceSOAP11port_http(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getOrderServiceSOAP12port_httpAddress();

    public com.appdynamics.inventory.OrderServicePortType getOrderServiceSOAP12port_http() throws javax.xml.rpc.ServiceException;

    public com.appdynamics.inventory.OrderServicePortType getOrderServiceSOAP12port_http(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}

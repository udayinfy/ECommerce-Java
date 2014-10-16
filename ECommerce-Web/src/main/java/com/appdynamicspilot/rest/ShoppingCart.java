package com.appdynamicspilot.rest;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.apache.log4j.Logger;

public class ShoppingCart implements java.io.Serializable {
	Logger log = Logger.getLogger(ShoppingCart.class);

	private List<ShoppingCartItem> items;
	
	public ShoppingCart() {
		items = new ArrayList<ShoppingCartItem>();
	}
	
	public void addItem(ShoppingCartItem item) {
		items.add(item);
	}
	
	public void removeItem(ShoppingCartItem item) {
		items.remove(item);
	}
	
	public List<ShoppingCartItem> getAllItems() {
		return items;
	}
	
	public double getCartTotal() {
		double total = 0.0;
		for (ShoppingCartItem item:items) {
			total += item.getPrice();
		}
		return total;
	}
	
	public void clear() {
		items.clear();
	}
}

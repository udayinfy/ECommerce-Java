package com.appdynamics.xml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class CartVO{
	private List items;
    
	public CartVO(){
		items=new ArrayList<ItemVO>();
	}
	
	public List getItems() {
		return items;
	}

	public void setItems(List items) {
		this.items = items;
	}
	
	
	

		
}

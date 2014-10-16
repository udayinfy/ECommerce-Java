package com.appdynamicspilot.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.appdynamicspilot.model.Item;
import com.appdynamicspilot.persistence.ItemPersistence;


public class ItemService {
	private static final Logger log = Logger.getLogger(ItemService.class);
	private ItemPersistence itemPersistence;

	public List<Item> getAllItems() {
		return itemPersistence.getAllItems();
	}

	public Item getItemByID(Long id){
		return itemPersistence.getItemByID(id);
	}

	public void setItemPersistence(ItemPersistence itemPersistence) {
		this.itemPersistence = itemPersistence;
	}
	
	public Item getItemByName(String name){
		return this.itemPersistence.getItemByName(name);
	}
}

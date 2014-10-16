package com.appdynamicspilot.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.appdynamicspilot.model.Item;
import com.appdynamicspilot.persistence.BasePersistenceImpl;
import com.appdynamicspilot.persistence.ItemPersistence;
import com.appdynamicspilot.service.ItemService;
import com.appdynamicspilot.util.SpringContext;

@Path("/items")
public class Items {
	private static final Logger LOGGER = Logger.getLogger(BasePersistenceImpl.class);
	
	 @Autowired
	 private ApplicationContext appContext;
	 
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Item getItem(@PathParam("id") Long id) {
		return getItemService().getItemByID(id);
	}
	
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_XML)
	public List<Item> getItems() {
		return getItemService().getAllItems();
	}
	
	public ItemService getItemService() {
		return (ItemService)SpringContext.getBean("itemService");
	}

}

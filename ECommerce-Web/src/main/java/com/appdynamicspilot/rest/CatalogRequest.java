package com.appdynamicspilot.rest;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import sun.dc.pr.PathDasher;
import com.appdynamicspilot.model.Item;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import com.appdynamicspilot.util.SpringContext;
import com.appdynamicspilot.service.ItemService;

@Path("/catalog")
public class CatalogRequest {
	public static long MAGIC_CRASH_NUMBER=27L;
	private static final Logger logger = Logger.getLogger(CatalogRequest.class.getName());
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Item getItem(@PathParam("id") Long id) {		
		if (id==27) {		
			PathDasher dasher = new PathDasher(null) ;
		} else if (id != null) {
			return getItemService().getItemByID(id);
		}
		return null;
	}
	
	public ItemService getItemService() {
		return (ItemService)SpringContext.getBean("itemService");
	}
	
	
}

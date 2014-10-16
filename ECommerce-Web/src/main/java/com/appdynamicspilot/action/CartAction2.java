package com.appdynamicspilot.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.appdynamicspilot.model.Item;
import com.appdynamicspilot.service.ItemService;
import com.appdynamicspilot.util.ArgumentUtils;
import com.appdynamicspilot.util.ServiceRegistry;
import com.appdynamicspilot.util.SpringContext;

public class CartAction2 extends ActionSupport  implements  Preparable, ServletResponseAware,ServletRequestAware {

	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private List<Item> itemsList;
	
	public List<Item> getItemsList() {
		return itemsList;
	}
	public void setItemsList(List<Item> itemsList) {
		this.itemsList = itemsList;
	}
	
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public String getAllItems() {
		if(ArgumentUtils.isNull(ActionContext.getContext().getSession().get("USER")))
			return "LOGOUT";
		ItemService itemService=(ItemService)SpringContext.getBean("itemService");
		itemsList=itemService.getAllItems();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>Received Item List >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		request.setAttribute("itemsList", itemsList);
		return "SUCCESS";
	}

}

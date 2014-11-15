package com.appdynamicspilot.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import com.appdynamicspilot.model.Item;
import com.appdynamicspilot.model.User;
import com.appdynamicspilot.service.CartService;
import com.appdynamicspilot.service.ItemService;
import com.appdynamicspilot.util.ArgumentUtils;

public class ItemAction extends ActionSupport implements Preparable, ServletRequestAware {
    private static final Logger log = Logger.getLogger(ItemAction.class);
    private Item item;
    private ItemService itemService;
    private CartService cartService;
    private List<Item> itemsList;
    private HttpServletRequest request;

    public void setServletRequest(HttpServletRequest arg0) {
        this.request = arg0;

    }

    public List<Item> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<Item> itemsList) {
        this.itemsList = itemsList;
    }

    public String getAllItems() {
        log.info("Inside the action class");
        User user = (User) ActionContext.getContext().getSession().get("USER");
        if (ArgumentUtils.isNull(user))
            return "LOGOUT";
        cartService.deleteCartItems(user.getId());

        itemsList = itemService.getAllItems();

        request.setAttribute("itemsList", itemsList);

        return "SUCCESS";
    }

    public void prepare() throws Exception {
        log.info("Inside the Prepare method");
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }
}

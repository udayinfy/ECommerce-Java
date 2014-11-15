package com.appdynamicspilot.servlet;

import com.appdynamicspilot.model.Cart;
import com.appdynamicspilot.service.CartService;
import com.appdynamicspilot.util.SpringContext;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by aleftik on 10/13/14.
 */
public class CartSessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent event) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        if (session != null) {
            Cart cart = (Cart) session.getAttribute("CART");
            if (cart != null) {
                CartService service = (CartService) SpringContext.getBean("cartService");
                service.deleteCart(cart);
            }
        }

    }
}

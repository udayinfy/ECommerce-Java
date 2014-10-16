package com.appdynamicspilot.service;

import java.util.List;

import com.appdynamicspilot.model.Cart;
import com.appdynamicspilot.model.Item;
import com.appdynamicspilot.persistence.CartPersistence;
import com.appdynamicspilot.webserviceclient.SoapUtils;

public interface CartServiceInterface {
	
	void setSoapUtil(SoapUtils soapUtil) ;
	List<Item> getAllCartItems(Long cartId);
	void saveItemInCart(Cart cart) ;
	void deleteCartItems(Long userId);
	void setCartPersistence(CartPersistence cartPersistence);
	Long checkOut(Long itemId,Integer quantity) throws Exception;
	void deleteItemInCart(Long id);
	Integer getCartSize(Long userId);
    List<Item> getAllItemsByUser(Long userId);
    void deleteCart(Cart cart);
}

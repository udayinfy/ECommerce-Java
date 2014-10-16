package com.appdynamicspilot.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.appdynamicspilot.model.Cart;
import com.appdynamicspilot.model.Item;
import com.appdynamicspilot.persistence.CartPersistence;
import com.appdynamicspilot.persistence.ItemPersistence;
import com.appdynamicspilot.webserviceclient.SoapUtils;


public class CartService implements CartServiceInterface {
	private static final Logger log = Logger.getLogger(CartService.class);
	private CartPersistence cartPersistence;
	private SoapUtils soapUtil;
	private ItemPersistence itemPersistence;
	
	
	public void setSoapUtil(SoapUtils soapUtil) {
		this.soapUtil = soapUtil;
	}
	public List<Item> getAllCartItems(Long cartId){
        return cartPersistence.getAllCartItems(cartId);
	}
	public void saveItemInCart(Cart cart) {
		 cartPersistence.save(cart); 
	}
	public void deleteCartItems(Long userId){
		cartPersistence.deleteAllCartItems(userId);
	}
	public void setCartPersistence(CartPersistence cartPersistence) {
		this.cartPersistence = cartPersistence;
	}
	
	public Long checkOut(Long itemId,Integer quantity) throws Exception{
		try{
			Item item=itemPersistence.getItemByID(itemId);
			System.out.println("Checking out >>>>>>>>"+item.getTitle()+"<<<<<<<<<<<<<<<<<<<<<<<<<");
			Long orderId=soapUtil.raisePO(itemId,quantity);
			//	return SoapUtils.getOrderId(SoapUtils.postSoapMessage(SoapUtils.getSoapMessageForCheckOut(itemId,quantity),"http://localhost:8080/cart/services/OrderService?wsdl"));
			return orderId;
		}catch(Exception e){
			throw e;
		}
	}

	/**
	 * This API is for generating error at inventory server . CartAction.sendItem will specify the wrong wsdl url here
	 * @param itemId
	 * @param quantity
	 * @param wsdlURL
	 * @return
	 * @throws Exception
	 */
	public Long checkOut(Long itemId,Integer quantity,String wsdlURL) throws Exception{
		try{
			Item item=itemPersistence.getItemByID(itemId);
			System.out.println("Checking out >>>>>>>>"+item.getTitle()+"<<<<<<<<<<<<<<<<<<<<<<<<<");
			Long orderId=soapUtil.raisePO(itemId,quantity,wsdlURL);
			//	return SoapUtils.getOrderId(SoapUtils.postSoapMessage(SoapUtils.getSoapMessageForCheckOut(itemId,quantity),"http://localhost:8080/cart/services/OrderService?wsdl"));
			return orderId;
		}catch(Exception e){
			throw e;
		}
	}
	
	public void deleteItemInCart(Long id) {
		//getCart
	}
	
	public Integer getCartSize(Long userId){
		return cartPersistence.getCartSize(userId);
	}
	public ItemPersistence getItemPersistence() {
		return itemPersistence;
	}
	public void setItemPersistence(ItemPersistence itemPersistence) {
		this.itemPersistence = itemPersistence;
	}
    public List<Item> getAllItemsByUser(Long userId) {
        return cartPersistence.getAllItemsByUser(userId);
    }
    public void deleteCart(Cart cart) {cartPersistence.deleteCart(cart);}
	
}

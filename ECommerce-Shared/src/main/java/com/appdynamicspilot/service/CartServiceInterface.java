/*
 * Copyright 2015 AppDynamics, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
	void deleteItemInCart(String username,Long id);
	Integer getCartSize(Long userId);
    List<Item> getAllItemsByUser(Long userId);
    void deleteCart(Cart cart);
}

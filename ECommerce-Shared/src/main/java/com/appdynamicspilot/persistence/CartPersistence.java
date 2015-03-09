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

package com.appdynamicspilot.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import com.appdynamicspilot.util.SpringContext;
import org.apache.log4j.Logger;

import com.appdynamicspilot.model.Cart;
import com.appdynamicspilot.model.Item;
import com.appdynamicspilot.model.User;
import javax.persistence.*;
import com.appdynamicspilot.util.ArgumentUtils;

import org.springframework.transaction.annotation.Transactional;

public class CartPersistence extends BasePersistenceImpl {
	/**
	 * Logger for this class
	 */
	private static final Logger LOGGER = Logger.getLogger(CartPersistence.class);

	public List<Item> getAllCartItems(Long cartId){
        Cart cart = getEntityManager().find(Cart.class,cartId);
        if (cart == null) {
            return Collections.EMPTY_LIST;
        }
        return cart.getItems();
	}

	@Transactional
    public void deleteAllCartItems(Long userId){
        EntityManager em = getEntityManager();
        EntityTransaction  txn = em.getTransaction();
        txn.begin();
        Query q = em.createQuery("DELETE FROM Cart c where c.user.id=:id");
        q.setParameter("id", userId);
        q.executeUpdate();
        txn.commit();
	}

	
	public Integer getCartSize(Long userId){
        List<Item> list = getAllItemsByUser(userId);
        return list.size();
	}

    public List<Item> getAllItemsByUser(Long userId) {
        Query q = getEntityManager().createQuery("SELECT c FROM Cart c where c.user.id = :userid");
        q.setParameter("userid",userId);
        List<Cart> carts = q.getResultList();
        if ((carts == null) || (carts.size() == 0)) {
            return Collections.EMPTY_LIST;
        }

        Cart cart = (Cart)  carts.get(0);
       return cart.getItems();
    }

    public void deleteCart(Cart cart) {
        if (getEntityManager() == null) {
            setEntityManager(findEntityManger());
        }
        if (cart != null) {
            Long cartId = cart.getId();
            if (cartId != null) {
                Cart attachedCart = getEntityManager().find(Cart.class, cartId);
                if (attachedCart != null) {
                    delete(attachedCart);
                }
            }
        }
    }

    private EntityManager findEntityManger() {
        EntityManagerHolder holder = (EntityManagerHolder) SpringContext.getBean("entityManagerHolder");
        EntityManagerFactory emf = holder.getEntityManagerFactory();
        return emf.createEntityManager();
    }

    public void deleteItemInCart(String username, Long id) {
        Query q = getEntityManager().createQuery("SELECT c FROM Cart c where c.user.email = :userid");
        q.setParameter("userid",username);
        Cart c = (Cart)q.getSingleResult();
        Item i = getEntityManager().find(Item.class, id);
        c.removeItem(i);
        update(c);
    }
}

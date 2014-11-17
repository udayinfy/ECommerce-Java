package com.appdynamicspilot.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

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

        Cart attachedCart = getEntityManager().find(Cart.class,cart.getId());
        if (attachedCart != null) {
            delete(attachedCart);
        }
    }

    private EntityManager findEntityManger() {
        EntityManagerHolder holder = (EntityManagerHolder) SpringContext.getBean("entityManagerHolder");
        emf = holder.getEntityManagerFactory();
        return emf.createEntityManager();
    }
}

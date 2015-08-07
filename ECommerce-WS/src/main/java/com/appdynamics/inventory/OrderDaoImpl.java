package com.appdynamics.inventory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appdynamicspilot.exception.InventoryServerException;

public class OrderDaoImpl implements OrderDao {

	private Logger logger = LoggerFactory.getLogger(OrderDaoImpl.class);
	

	InventoryItem item = null;
	public static final int SLOW_BOOK = 3;
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	private String selectQuery = null;

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public void setEntityManagerFactory(
			EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public synchronized EntityManager getEntityManager() {
		if (entityManager == null) {
			
			entityManager = getEntityManagerFactory().createEntityManager();
			
			if(entityManager == null){
				logger.info("Manager not found");
			}
		}
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Long createOrder(OrderRequest orderRequest) throws InventoryServerException {
    	
		logger.debug(orderRequest.getItemId() + ": is the Item Id");
        	
    	InventoryItem item = getEntityManager().find(InventoryItem.class,orderRequest.getItemId());

    	logger.debug("inside create Order in the Order Web Service");
    	/**
         * Throws an error if the item ID is 5
         */
        if (orderRequest.getItemId() == 5) {
            throw new InventoryServerException("Error in creating order for " + item.getId(), null);
        }
        return storeOrder(orderRequest);
    }

	private Long storeOrder(OrderRequest orderRequest) {
		InventoryItem item = entityManager.find(InventoryItem.class,
				orderRequest.getItemId());

		Order order = new Order(orderRequest, item);
		
		logger.info("order stored is: " +order.id + " " +order.quantity + " " +order.createdOn);
		
		order.setQuantity(orderRequest.getQuantity());
		
		persistOrder(order);
		
		// deleting the order to reduce size of data
		removeOrder(order);
		return order.getId();
	}

	private void persistOrder(Order order) {
		EntityTransaction txn = getEntityManager().getTransaction();
		
		
		try {
			txn.begin();
			entityManager.persist(order);
		} catch (Exception ex) {
			logger.error(ex.toString());
			txn.rollback();
		} finally {
			if (!txn.getRollbackOnly()) {
				txn.commit();
			}
		}
	}

	private void removeOrder(Order order) {
		EntityTransaction txn = getEntityManager().getTransaction();
		try {
			txn.begin();
			entityManager.remove(order);
		} catch (Exception ex) {
			logger.error(ex.toString());
			txn.rollback();
		} finally {
			if (!txn.getRollbackOnly()) {
				txn.commit();
			}
		}
	}

	/**
	 * @param selectQuery
	 *            the selectQuery to set
	 */
	public void setSelectQuery(String selectQuery) {
		this.selectQuery = selectQuery;
	}
}
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

package com.appdynamics.inventory;

import com.appdynamicspilot.exception.InventoryServerException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.apache.log4j.Logger;


public class OrderDaoImpl implements OrderDao {
    private Logger logger = Logger.getLogger(OrderDaoImpl.class);
    private EntityManagerFactory entityManagerFactory;

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    private EntityManager entityManager;

    private String selectQuery = null;

    public synchronized EntityManager getEntityManager() {
       if (entityManager ==null) {
          entityManager = getEntityManagerFactory().createEntityManager();
       }
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Long createOrder(OrderRequest orderRequest) throws InventoryServerException {
        InventoryItem item = getEntityManager().find(InventoryItem.class,orderRequest.getItemId());
        if (orderRequest.getItemId() == 5) {
            throw new InventoryServerException("Error in creating order for " + item.getId(), null);
        }


        try {
            Query q = getEntityManager().createNativeQuery(this.selectQuery);
            q.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         *
         */
        QueryExecutor qe = new QueryExecutor();
        if (orderRequest.getItemId() == 3) {
            qe.executeSimplePS(10000);
        } else {
            qe.executeSimplePS(10);
        }
        return storeOrder(orderRequest);
    }

    private Long storeOrder(OrderRequest orderRequest) {
        InventoryItem item = entityManager.find(InventoryItem.class,orderRequest.getItemId());
        Order order = new Order(orderRequest,item);

        order.setQuantity(orderRequest.getQuantity());
        persistOrder(order);
        //deleting the order to reduce size of data
        removeOrder(order);
        return order.getId();
    }

    private void persistOrder(Order order) {
        EntityTransaction txn = getEntityManager().getTransaction();
        try {
            txn.begin();
            entityManager.persist(order);
        } catch (Exception ex) {
             logger.error(ex);
             txn.rollback();
        } finally {
            if(!txn.getRollbackOnly()) {
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
            logger.error(ex);
            txn.rollback();
        } finally {
            if(!txn.getRollbackOnly()) {
                txn.commit();
            }
        }
    }

    /**
     * @param selectQuery the selectQuery to set
     */
    public void setSelectQuery(String selectQuery) {
        this.selectQuery = selectQuery;
    }


}
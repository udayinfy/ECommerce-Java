/**
 * 
 */
package com.appdynamicspilot.persistence;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;


/**
 * @author Vikash
 *
 */
public class BasePersistenceImpl {

    private final static Logger LOGGER = Logger.getLogger(BasePersistenceImpl.class.getName());

    private EntityManagerFactory emf = null;

    private EntityManager em = null;

    private static final int DEFAULT_INTERNAL = 15;

    private int interval = DEFAULT_INTERNAL;


    /**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BasePersistenceImpl.class);

	/**
	 * The method to update the serailizable business objects into the database.
	 * 
	 * @param object --
	 *            serializable object
	 * @throws PersistenceException
	 */
    @Transactional
    public void update(final Serializable object) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();
        try {
            entityManager.merge(object);
        } catch (Exception ex) {
            logger.error(ex);
            txn.rollback();
        } finally {
            if (!txn.getRollbackOnly()) {
                txn.commit();
            }
        }
    }


    public EntityManagerFactory getEntityManagerFactory() {
        return this.emf;
    }

    public void setEntityManagerFactory(EntityManagerFactory factory) {
         this.emf = factory;
    }


    public EntityManager getEntityManager() {
           return EntityManagerHelper.getInstance().getEntityManager();
    }

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Transactional
	public void save(final Serializable object) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();
        try {
            entityManager.persist(object);
        } catch (Exception ex) {
            logger.error(ex);
            txn.rollback();
        } finally {
            if (!txn.getRollbackOnly()) {
                txn.commit();
            }
        }

	}

    @Transactional
    public void delete(Serializable object){
        EntityManager entityManager = getEntityManager();
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();
        try {
            entityManager.remove(object);
        } catch (Exception ex) {
            logger.error(ex);
            txn.rollback();
        } finally {
            if (!txn.getRollbackOnly()) {
                txn.commit();
            }
        }
	}

    /**
     * @return the interval
     */
    public int getInterval() {
        return interval;
    }

    /**
     * @param interval
     *            the interval to set
     */
    public void setInterval(int interval) {
        if (interval < 0) {
            LOGGER.warn("Invalid interval: " + interval + "; setting to default: " + DEFAULT_INTERNAL);
            this.interval = DEFAULT_INTERNAL;
        } else {
            this.interval = interval;
        }
    }

    protected boolean shouldFireSlow() {
        return (Math.random() * 100) <= interval;
    }


}

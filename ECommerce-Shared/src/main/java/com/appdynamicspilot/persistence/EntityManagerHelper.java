package com.appdynamicspilot.persistence;

import javax.persistence.EntityManager;

/**
 * Created by aleftik on 10/8/14.
 */
public class EntityManagerHelper {
    private static ThreadLocal<EntityManager> localRef = new ThreadLocal<EntityManager>();
    private static final EntityManagerHelper instance = new EntityManagerHelper();

    private EntityManagerHelper() {

    }

    public static EntityManagerHelper getInstance() {
        return instance;
    }

    public EntityManager getEntityManager() {
        return localRef.get();
    }

    public void setEntityManager(EntityManager em) {
        localRef.set(em);
    }
}

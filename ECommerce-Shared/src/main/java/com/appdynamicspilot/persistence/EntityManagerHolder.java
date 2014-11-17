package com.appdynamicspilot.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

/**
 * Created by aleftik on 10/8/14.
 */
public class EntityManagerHolder {

    private EntityManagerFactory em;

    public void setEntityManagerFactory(EntityManagerFactory em) {
        this.em = em;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return this.em;
    }
}

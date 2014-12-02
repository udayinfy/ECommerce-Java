package com.appdynamicspilot.rest;

import com.appdynamicspilot.model.FulfillmentOrder;
import com.appdynamicspilot.persistence.EntityManagerHolder;
import com.appdynamicspilot.util.SpringContext;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * Created by aleftik on 11/15/14.
 */
@Path("fulfillment")
public class Fulfillment {

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public void fulfillOrder(FulfillmentOrder order) {
            EntityManager mgr = findEntityManager();
            Query q = mgr.createNativeQuery("SELECT * FROM Zips WHERE upper(City)='SPRINGFIELD'");
            q.getResultList();
    }

    public EntityManager findEntityManager() {
        EntityManagerHolder holder = (EntityManagerHolder) SpringContext.getBean("entityManagerHolder");
        EntityManagerFactory emf = holder.getEntityManagerFactory();
        return emf.createEntityManager();
    }
}

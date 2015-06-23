package com.appdynamicspilot.persistence;

import com.appdynamicspilot.model.Fault;
import org.apache.log4j.Logger;

import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

/**
 * Created by swetha.ravichandran on 6/19/15.
 */
public class FaultPersistence extends BasePersistenceImpl  {

    /**
     * Logger for this class
     */
    private static final Logger log = Logger.getLogger(FaultPersistence.class);

    /**
     * Get all the bugs for Fault Injection based on user
     *
     * @param username
     * @return List of Bugs
     */
    public List<Fault> getallbugsbyuser(String username) {
        Query q = getEntityManager().createQuery("SELECT f FROM Fault f where f.username = :username");
        q.setParameter("username", username);
        List<Fault> fibugs = q.getResultList();
        if ((fibugs == null) || (fibugs.size() == 0)) {
            return Collections.EMPTY_LIST;
        }

        return fibugs;
    }
}

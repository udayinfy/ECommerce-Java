package com.appdynamicspilot.service;

import com.appdynamicspilot.model.Fault;
import com.appdynamicspilot.persistence.FaultPersistence;

import java.util.List;

/**
 * Created by swetha.ravichandran on 6/19/15.
 */
public class FaultService implements FaultServiceInterface {

    /**
     * Ref to FaultPersistence class
     */
    public FaultPersistence getFaultPersistence() {
        return faultPersistence;
    }

    public void setFaultPersistence(FaultPersistence faultPersistence) {
        this.faultPersistence = faultPersistence;
    }

    private FaultPersistence faultPersistence;

    /**
     * Inserts Cart and cart-Item to the corresponding tables
     *
     * @param fault object
     */
    public void SaveFIBugs(Fault fault) {
        faultPersistence.save(fault);
    }

    public List<Fault> getallbugsbyuser(String username) {
        return faultPersistence.getallbugsbyuser(username);
    }
}

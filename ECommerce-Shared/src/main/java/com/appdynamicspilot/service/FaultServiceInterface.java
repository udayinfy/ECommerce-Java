package com.appdynamicspilot.service;

import com.appdynamicspilot.model.Fault;

import java.util.List;

/**
 * Created by swetha.ravichandran on 6/19/15.
 */
public interface FaultServiceInterface {

    void SaveFIBugs(Fault fibugs);

    List<Fault> getallbugsbyuser(String username);
}

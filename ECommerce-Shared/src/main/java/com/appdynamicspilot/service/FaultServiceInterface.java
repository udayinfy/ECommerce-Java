package com.appdynamicspilot.service;

import com.appdynamicspilot.model.Fault;

import java.util.List;

/**
 * Created by swetha.ravichandran on 6/19/15.
 */
public interface FaultServiceInterface {

    void saveFIBugs(Fault fibugs);

    List<Fault> getAllBugsByUser(String username);
}

package com.appdynamicspilot.restv2;

import com.appdynamicspilot.model.Fault;
import com.appdynamicspilot.service.FaultService;
import com.appdynamicspilot.util.FaultUtils;
import com.appdynamicspilot.util.SpringContext;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by swetha.ravichandran on 7/2/15.
 */
@Path("/json/fault")
public class Faults {

    private static final Logger log = Logger.getLogger(Faults.class.getName());
    FaultUtils faultUtils = new FaultUtils();
    /**
     * Gets FaultService bean
     *
     * @return FaultService
     */
    public FaultService getFIBugService() {
        return (FaultService) SpringContext.getBean("faultService");
    }

    //Used in Fault Injection to store the faultinfo - user, bug name , Time Frame
    @Path("/savefaults")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String saveBugsForFaultInjection(List<Fault> lsFault) throws Exception {
        String returnMessage = "";
        String userName = "";
        try {
            if (lsFault != null && lsFault.size() > 0) {
                for (Fault fault : lsFault) {
                    userName = fault.getUsername();
                    getFIBugService().saveFIBugs(fault);
                }
                faultUtils.saveCaching(userName, lsFault);
                returnMessage = "Fault(s) injected successfully";
            } else {
                returnMessage = "No Fault received";
            }

        } catch (Exception ex) {
            log.error(ex);
        }
        return returnMessage;
    }

    //Used in Fault Injection to inject the fault now
    @Path("/injectfaults")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String injectBugsForFaultInjection(List<Fault> lsFault) throws Exception {
        String returnMessage = "";
        try {
            if (lsFault != null && lsFault.size() > 0) {
                faultUtils.injectFault(lsFault,true);
                returnMessage = "Fault(s) injected successfully";
            } else {
                returnMessage = "No Fault received";
            }

        } catch (Exception ex) {
            log.error(ex);
        }
        return returnMessage;
    }

    //Used in Fault Injection to store the faultinfo - user, bug name , Time Frame
    @Path("/readfaults")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Fault> readBugsForFaultInjection(@Context HttpServletRequest req) throws Exception {
        String username = req.getHeader("USERNAME");
        List<Fault> lsFault = new ArrayList<Fault>();
        try {
            if (!StringUtils.isBlank(username)) {
                lsFault = getFIBugService().getAllBugsByUser(username);
            }
        } catch (Exception ex) {
            log.error(ex);
        }
        return lsFault;
    }



}

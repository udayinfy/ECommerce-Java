package com.appdynamicspilot.restv2;

import com.appdynamicspilot.model.Fault;
import com.appdynamicspilot.service.FaultService;
import com.appdynamicspilot.util.FaultUtils;
import com.appdynamicspilot.util.SpringContext;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.container.TimeoutHandler;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    /**
     * Save the faults and can be injected later based on time frame.
     *
     * @param lsFault
     * @return
     * @throws Exception
     */
    @Path("/savefaults")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String saveFaults(List<Fault> lsFault) throws Exception {
        String returnMessage = "";
        String userName = "";
        try {
            if (lsFault != null && lsFault.size() > 0) {
                for (Fault fault : lsFault) {
                    userName = fault.getUsername();
                    getFIBugService().saveFaults(fault);
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

    /**
     * Inject the faults now
     *
     * @param lsFault
     * @return
     * @throws Exception
     */
    @Path("/injectfaults")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String injectFaults(List<Fault> lsFault) throws Exception {
        String returnMessage = "";
        try {
            if (lsFault != null && lsFault.size() > 0) {
                faultUtils.injectFault(lsFault, true);
                returnMessage = "Fault(s) injected successfully";
            } else {
                returnMessage = "No Fault received";
            }

        } catch (Exception ex) {
            log.error(ex);
        }
        return returnMessage;
    }

    /**
     * Read the faults based on user
     *
     * @param req
     * @return fault information
     * @throws Exception
     */
    @Path("/readfaults")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Fault> readFaultsByUser(@Context HttpServletRequest req) throws Exception {
        String userName = req.getHeader("USERNAME");
        List<Fault> lsFault = new ArrayList<Fault>();
        try {
            if (!StringUtils.isBlank(userName)) {
                lsFault = getFIBugService().getAllFaultsByUser(userName);
            }
        } catch (Exception ex) {
            log.error(ex);
        }
        return lsFault;
    }

    /**
     * Read all the faults
     *
     * @return
     * @throws Exception
     */
    @Path("/readallfaults")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Fault> readAllFaults() throws Exception {
        List<Fault> lsFault = new ArrayList<Fault>();
        try {
            lsFault = getFIBugService().getAllFaults();
        } catch (Exception ex) {
            log.error(ex);
        }
        return lsFault;
    }

    /**
     * Stop the injected faults based on user
     *
     * @param req
     * @return deleted rows
     * @throws Exception
     */
    @Path("/stopfaults")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public int stopFaultsByUser(@Context HttpServletRequest req) throws Exception {
        String userName = req.getHeader("USERNAME");
        String faultName = req.getHeader("FAULTNAME");
        int deletedRows = 0;
        try {
            deletedRows = getFIBugService().deleteFaults(userName, faultName);
            faultUtils.deleteCaching(userName, faultName);
        } catch (Exception ex) {
            log.error(ex);
        }
        return deletedRows;
    }

    /**
     * Inject faults based on time frame in load-gen
     *
     * @param req
     * @return
     * @throws Exception
     */
    @Path("/getfaults")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Fault> getFaults(@Context HttpServletRequest req) throws Exception {

        String username = req.getHeader("USERNAME");
        return getAllFaults(username);
    }

    /**
     * Inject the faults now
     *
     * @return
     * @throws Exception
     */
    @Path("/injectsavedfaults")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public void injectSavedFaultsAsync(@Suspended final AsyncResponse asyncResponse, @Context final HttpServletRequest req) throws Exception {
        try {
            final String username = req.getHeader("USERNAME");
            final List<Fault> lsFault = getAllFaults(username);
            if (lsFault != null && lsFault.size() > 0 && lsFault.get(0).getUsername().trim().equalsIgnoreCase(username.trim())) {
                log.info(lsFault.size());
            }

            asyncResponse.setTimeoutHandler(new TimeoutHandler() {
                @Override
                public void handleTimeout(AsyncResponse asyncResponse) {
                    asyncResponse.resume(Response.status(Response.Status.SERVICE_UNAVAILABLE)
                            .entity("Operation time out.").build());
                }
            });
            asyncResponse.setTimeout(6, TimeUnit.MINUTES);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    String result = veryExpensiveOperation();
                    asyncResponse.resume(result);
                }

                private String veryExpensiveOperation() {
                    // ... very expensive operation that typically finishes within 6 minutes
                    if (lsFault != null && lsFault.size() > 0) {
                        return faultUtils.injectFault(lsFault, false);
                    }
                    return "No Faults being injected.";
                }
            }).start();
        } catch (Exception ex) {
            log.error(ex);
        }
    }

    private List<Fault> getAllFaults(String username) {
        List<Fault> lsFault = new ArrayList<Fault>();
        try {

            boolean cache = true;
            FaultUtils faultUtils = new FaultUtils();
            log.info(username);
            if (!StringUtils.isBlank(username)) {
                if (faultUtils.readCaching(username) != null) {
                    lsFault = faultUtils.readCaching(username);
                    if (lsFault.size() > 0 && lsFault.get(0).getUsername().trim().equalsIgnoreCase(username.trim())) {
                        log.info("From Caching , Fault size: " + lsFault.size());
                    } else {
                        cache = false;
                    }
                } else {
                    cache = false;
                }
                if (!cache) {
                    lsFault = getFIBugService().getAllFaultsByUser(username);
                    if (lsFault != null && lsFault.size() > 0 && lsFault.get(0).getUsername().trim().equalsIgnoreCase(username.trim())) {
                        log.info("From DB , Fault size: " + lsFault.size());
                    }
                }
            }

            if (lsFault != null && lsFault.size() > 0) {
                return lsFault;
            }
        } catch (Exception ex) {
            log.error(ex);
        }
        return lsFault;
    }


}

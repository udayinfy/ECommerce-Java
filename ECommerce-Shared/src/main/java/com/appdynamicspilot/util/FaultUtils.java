package com.appdynamicspilot.util;

import com.appdynamicspilot.faultinjection.FaultInjection;
import com.appdynamicspilot.faultinjection.FaultInjectionFactory;
import com.appdynamicspilot.model.Fault;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by swetha.ravichandran on 7/2/15.
 */
public class FaultUtils {

    private static final Logger log = Logger.getLogger(FaultUtils.class.getName());


    /**
     * Injects Faults
     *
     * @param lsFault - List of faults available
     */
    public void injectFault(List<Fault> lsFault, boolean injectNow) {
        for (Fault fault : lsFault) {
            //Parsing time frame and calling the inject fault method based on time and user.
            if (!injectNow && checkTime(fault.getTimeframe())) {
                instantiateFault(fault);
            } else if (injectNow) {
                instantiateFault(fault);
            }
        }
    }

    /**
     * Helper for time frame parser and comparison
     *
     * @param timeFrame
     * @return
     * @throws Exception
     */
    private boolean checkTime(String timeFrame) {
        //Variables used for comparison with current date in the parsed format.
        Date parsedStartTime = null, parsedEndTime = null, parsedCurrentTime = null;
        Calendar cal = new GregorianCalendar();

        //Parsing the date according to Hours, Minutes set on the UI and setting the Locale to US.
        SimpleDateFormat parser = new SimpleDateFormat("HH:mm", Locale.US);

        String startTimeString = timeFrame.substring(0, 5);
        String endTimeString = timeFrame.substring(8);

        String currentTime = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE);


        try {
            parsedStartTime = parser.parse(startTimeString);
            parsedEndTime = parser.parse(endTimeString);
            parsedCurrentTime = parser.parse(currentTime);
            //returns only if the time is within the time range selected on the UI.
            if (parsedCurrentTime.after(parsedStartTime) && parsedCurrentTime.before(parsedEndTime)) {
                return true;
            }
        } catch (ParseException e) {
            log.error(e);
        }
        return false;
    }

    /**
     * Instantiate the fault
     *
     * @param fault
     */
    private void instantiateFault(Fault fault) {
        //Creating Fault injection object parsing the bugName removing spaces.
        FaultInjectionFactory fiFactory = new FaultInjectionFactory();
        FaultInjection fi = fiFactory.getFaultInjection(fault.getBugname().replace(" ", ""));
        if (fi != null) {
            fi.injectFault();
        }
    }

    /**
     * Save Caching
     *
     * @param userName
     * @param lsFault
     */
    public void saveCaching(String userName, List<Fault> lsFault) {
        //Check if cache already exists
        List<Fault> lsFaultFromCache = (List<Fault>) CacheManager.getInstance().get(userName + "faultCache");
        if (lsFaultFromCache != null && lsFaultFromCache.size() > 0) {
            //If yes, get the existing list and add it to the newly created list
            for (Fault fault : lsFault) {
                lsFaultFromCache.add(fault);
            }
            CacheManager.getInstance().clear(userName + "faultCache");
            CacheManager.getInstance().put(userName + "faultCache", lsFaultFromCache);
        } else {
            CacheManager.getInstance().clear(userName + "faultCache");
            CacheManager.getInstance().put(userName + "faultCache", lsFault);
        }
    }

    /**
     * Read Caching
     *
     * @param userName
     * @return
     */
    public List<Fault> readCaching(String userName) {
        return (List<Fault>) CacheManager.getInstance().get(userName + "faultCache");
    }

    /**
     * Delete Caching
     *
     * @param userName
     * @param faultName
     */
    public void deleteCaching(String userName, String faultName) {
        List<Fault> lsFaultFromCache = (List<Fault>) CacheManager.getInstance().get(userName + "faultCache");
        if (lsFaultFromCache != null && lsFaultFromCache.size() > 0) {

            for(int i=0; i<lsFaultFromCache.size(); i++) {
                if (lsFaultFromCache.get(i).getUsername().equals(userName.trim()) && lsFaultFromCache.get(i).getBugname().equals(faultName.trim())) {
                    lsFaultFromCache.remove(i);
                }
            }
            CacheManager.getInstance().clear(userName + "faultCache");
            CacheManager.getInstance().put(userName + "faultCache", lsFaultFromCache);
        }
    }

}

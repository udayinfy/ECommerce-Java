package com.appdynamicspilot.faultinjection;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by shiv.loka on 6/24/15.
 */
public class MemoryLeakInjection implements FaultInjection {

    private static final Logger log = Logger.getLogger(MemoryLeakInjection.class);
    public static final int DEFAULT_CLEAR_PERCENT = 90;
    private String faultName = null;



    /**
     * variables used for causing memory leak
     */
    private static final int OBJECT_COUNT = 25;
    private static final int OBJECT_SIZE_IN_BYTES = 20;
    private List<byte[]> list = new ArrayList<byte[]>(DEFAULT_CLEAR_PERCENT);
    private AtomicInteger clearPercent = new AtomicInteger(
            DEFAULT_CLEAR_PERCENT);

    @Override
    public String injectFault() {

        Random random = new Random();

        /*this block is used to cause memory leak on the server*/

        causeMemoryLeak(OBJECT_COUNT, OBJECT_SIZE_IN_BYTES);

        return "Caused memory leak on the server";
    }

    @Override
    public String injectFault(List<String> params) {
        return null;
    }

    @Override
    public void test() {

    }

    /**
     * Causes memory leak on the server
     */
    public void causeMemoryLeak(int objectCount, int objectSizeInByte) {
        if (objectCount <= 0) {
            throw new IllegalArgumentException("Invalid objectCount");
        }
        if (objectSizeInByte <= 0) {
            throw new IllegalArgumentException("Invalid objectSizeInByte");
        }

        boolean isDebug = log.isDebugEnabled();
        Runtime runtime = Runtime.getRuntime();

        for (int i = 0; i < objectCount; i++)
        {
            byte[] copy = new byte[objectSizeInByte];
            synchronized (this.list) {
                this.list.add(copy);
            }

            long freeMemory = runtime.freeMemory();
            long totalMemory = runtime.totalMemory();
            double freePercent = ((double) freeMemory / (double) totalMemory) * 100;
            if (isDebug) {
                log.debug("Free memory: " + freeMemory + "; Total Memory: "
                        + totalMemory);
            }

            if (freePercent < (double) (100 - this.clearPercent.get())) {
                if (isDebug) {
                    log.debug("Clearing memory leak");
                }
                this.clear();
            }
        }
    }

    /**
     * Helper class for memory Leak bug
     * clear all object from this service
     */
    public void clear() {
        synchronized (this.list) {
            this.list.clear();
        }
    }

    /**
     * Helper class for memory Leak bug
     *  Get the current count of object
     * @return
     */
    public int getSize() {
        synchronized (this.list) {
            return this.list.size();
        }
    }

    public int getClearPercent() {
        return this.clearPercent.get();
    }

    /**
     * Helper class for memory Leak bug
     * Setting the threshold to clear the data structure preventing crashing the
     * JVM
     * @param clearPercent
     */
    public void setClearPercent(int clearPercent) {
        if (clearPercent < 0 || clearPercent > 100) {
            throw new IllegalArgumentException(
                    "Invalid clearPercent; Expecting value between 0 to 99");
        }
        this.clearPercent.set(clearPercent);
    }


}

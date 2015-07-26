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



    /**
     * variables used for causing memory leak
     */
    public static final int DEFAULT_CLEAR_PERCENT = 50;
    private static final int OBJECT_COUNT = 50000;
    private static final int OBJECT_SIZE_IN_BYTES = 50000;
    private List<byte[]> list = new ArrayList<byte[]>(DEFAULT_CLEAR_PERCENT);
    private AtomicInteger clearPercent = new AtomicInteger(
            DEFAULT_CLEAR_PERCENT);

    @Override
    public void injectFault() {
        /*this block is used to cause memory leak on the server*/
        long startTime = System.currentTimeMillis();
        causeMemoryLeak(OBJECT_COUNT, OBJECT_SIZE_IN_BYTES);
        long endTime = System.currentTimeMillis();
        log.info("Caused Memory Leak for " + (endTime - startTime) + " milliseconds");
        clearMemory();
    }

    /**
     * Causes memory leak on the server
     */
    private void causeMemoryLeak(int objectCount, int objectSizeInByte) {
        for (int i = 0; i < objectCount; i++)
        {
            byte[] copy = new byte[objectSizeInByte];
            synchronized (this.list) {
                this.list.add(copy);
            }
        }
    }

    private void clearMemory(){

        Runtime runtime = Runtime.getRuntime();
        long freeMemory = runtime.freeMemory();
        long totalMemory = runtime.totalMemory();
        double freePercent = ((double) freeMemory / (double) totalMemory) * 100;
        if (freePercent < (double) (100 - this.clearPercent.get())) {
            this.clear();
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
}

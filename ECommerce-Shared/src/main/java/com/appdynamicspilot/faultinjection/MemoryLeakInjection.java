package com.appdynamicspilot.faultinjection;

import org.apache.log4j.Logger;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shiv.loka on 6/24/15.
 */
public class MemoryLeakInjection implements FaultInjection {

    public static final int DEFAULT_CLEAR_PERCENT = 10;
    private static final Logger log = Logger.getLogger(MemoryLeakInjection.class);
    /**
     * variables used for causing memory leak
     */
    private static final int OBJECT_COUNT = 100;

    @Override
    public void injectFault() {
        /*this block is used to cause memory leak on the server*/
        long startTime = System.currentTimeMillis();
        causeMemoryLeak();
        long endTime = System.currentTimeMillis();
        log.info("Caused Memory Leak for " + (endTime - startTime) + " milliseconds");
    }

    /**
     * Causes memory leak on the server
     */
    private void causeMemoryLeak() {
        try {
            List<byte[]> list = new ArrayList<byte[]>();

            ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
            log.info("Heap : " + ManagementFactory.getMemoryMXBean().getHeapMemoryUsage());

            for(int i = 0; i < 300000 ; i ++){
                byte[] copy = new byte[1024];
                synchronized (list) {
                    list.add(copy);
                }
                if(i%1000 == 0) {
                    log.info(i);
                    Thread.sleep(1000);
                    ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
                    log.info("Heap : " + ManagementFactory.getMemoryMXBean().getHeapMemoryUsage());
                }
            }

        } catch (Exception ex) {
            log.info(ex.getMessage());
        }
    }
}

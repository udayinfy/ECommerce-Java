package com.appdynamicspilot.faultinjection;

import org.apache.log4j.Logger;

import java.lang.management.ManagementFactory;
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
            log.info("Heap : " + ManagementFactory.getMemoryMXBean().getHeapMemoryUsage());
            long usedMemory = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed();
            long totalMemory = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getMax();
            double usedPercentage = ((double) usedMemory / (double) totalMemory) * 100.0;
            int i = 0;
            while (usedPercentage <= 83) {
                byte[] copy = new byte[1024];
                synchronized (list) {
                    list.add(copy);
                }
                i++;
                if (i % 1000 == 0) {
                    log.info(i);
                    Thread.sleep(1000);
                    log.info("Heap : " + ManagementFactory.getMemoryMXBean().getHeapMemoryUsage());
                    usedMemory = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed();
                    totalMemory = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getMax();
                    usedPercentage = ((double) usedMemory / (double) totalMemory) * 100.0;
                    log.info("usedPercentage : " + usedPercentage);
                }
            }

        } catch (Exception ex) {
            log.info(ex.getMessage());
        }
    }
}

package com.appdynamicspilot.faultinjection;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by shiv.loka on 6/24/15.
 */
public class CPUBurnerInjection implements FaultInjection {

    private static final Logger log = Logger.getLogger(CPUBurnerInjection.class);
    public static final int DEFAULT_CLEAR_PERCENT = 90;



    @Override
    public void injectFault() {
        long startTime = System.currentTimeMillis();
        causeCPUBurn(30000);
        long endTime = System.currentTimeMillis();
        log.info("Caused CPU Burn for " + (endTime - startTime) + " milliseconds");
    }

    /**
     * CPU Burner method.
     * @param n
     * @return
     */
    public static long causeCPUBurn(int n) {
        long startTime = System.currentTimeMillis();
        long count = 0;
        while ((System.currentTimeMillis() - startTime) < n) {
            String s = someRandomString();
            for (int i = 0; i < 7; i++) {
                s += someRandomString();
            }
            count++;
        }
        return count;
    }

    /**
     * Helper to create a random string burning CPU
     * @return
     */
    public static String someRandomString() {
        String s1 = UUID.randomUUID().toString();
        double d = Math.pow(
                Math.sqrt(Math.pow(new Random().nextDouble(),
                        new Random().nextDouble())),
                Math.sqrt(Math.pow(new Random().nextDouble(),
                        new Random().nextDouble())));
        String s2 = new String(Double.toString(d));
        return s1 + s2;
    }
}

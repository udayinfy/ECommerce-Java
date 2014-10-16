package com.appdynamicspilot.action;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Random;
import java.util.UUID;

/**
 *
 * @author jayesh
 */
public class CpuBurner {

    /**
     * @param args the command line arguments
     */
    public static void burnCpu() {
    	Random random = new Random();
    	doSomething(random.nextInt(1000));
    }

    private static long doSomething(int n)
    {
            long startTime = System.currentTimeMillis();
            long count = 0;
            while ((System.currentTimeMillis() - startTime) < n)
            {
                    String s = someRandomString();
                    for (int i = 0; i < 7; i++)
                    {
                            s += someRandomString();
                    }
                    count++;
            }
            return count;
    }

    private static String someRandomString()
    {
            String s1 = UUID.randomUUID().toString();
            double d = Math.pow(Math.sqrt(Math.pow(new Random().nextDouble(), new Random().nextDouble())), Math.sqrt(Math
                            .pow(new Random().nextDouble(), new Random().nextDouble())));
            String s2 = new String(Double.toString(d));
            return s1 + s2;
    }   
    
}

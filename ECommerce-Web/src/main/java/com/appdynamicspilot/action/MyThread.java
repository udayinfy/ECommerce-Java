package com.appdynamicspilot.action;

public class MyThread extends Thread {

    private String threadName;

    public MyThread(String name) {
        threadName = name;
    }

    public void run() {
        try {
            for (int i = 0; i < 1000; i++) {
                // System.out.println("Thread " + threadName + " : " + i);
                Thread.sleep(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void consumeCpu() {
        for (int i = 0; i < 10; i++) {
            Thread thread1 = new MyThread("A" + i);
            thread1.start();
            Thread thread2 = new MyThread("B" + i);
            thread2.start();
        }
    }
}
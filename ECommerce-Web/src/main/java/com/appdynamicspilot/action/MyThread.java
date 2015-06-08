/*
 * Copyright 2015 AppDynamics, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*package com.appdynamicspilot.action;

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
}*/
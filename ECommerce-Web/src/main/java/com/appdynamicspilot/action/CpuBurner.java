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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *

import java.util.Random;
/*import java.util.UUID;

/**
 * @author jayesh
 *
public class CpuBurner {
    public static void burnCpu() {
        Random random = new Random();
        doSomething(random.nextInt(1000));
    }

    private static long doSomething(int n) {
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

    private static String someRandomString() {
        String s1 = UUID.randomUUID().toString();
        double d = Math.pow(Math.sqrt(Math.pow(new Random().nextDouble(), new Random().nextDouble())), Math.sqrt(Math
                .pow(new Random().nextDouble(), new Random().nextDouble())));
        String s2 = new String(Double.toString(d));
        return s1 + s2;
    }

}
*/
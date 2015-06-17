package com.appdynamicspilot.faultinjection;

import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by shiv.loka on 6/12/15.
 */
public class ServerFaultInjection implements FaultInjection {
	public static final int DEFAULT_CLEAR_PERCENT = 90;

	/**
	 * This method is to inject fault without any params supplied by the user.
	 */
	public String injectFault() {
		long startTime = System.currentTimeMillis();
		Random random = new Random();
		doSomething(random.nextInt(1000));
		long endTime = System.currentTimeMillis();
		
		return "caused cpu usage and time consumed is:" + (endTime - startTime);
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
		double d = Math.pow(
				Math.sqrt(Math.pow(new Random().nextDouble(),
						new Random().nextDouble())),
				Math.sqrt(Math.pow(new Random().nextDouble(),
						new Random().nextDouble())));
		String s2 = new String(Double.toString(d));
		return s1 + s2;
	}

	/**
	 * This method accepts params and sets up fault according to user inputs.
	 */
	public String injectFault(List<String> params) {
		return null;
	}

	public void test() {

	}

}

package com.appdynamicspilot.faultinjection;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by shiv.loka on 6/12/15.
 */
public class ServerFaultInjection implements FaultInjection {

	public static final int DEFAULT_CLEAR_PERCENT = 90;

	/**
	 * variables used for causing memory leak
	 */
	private static final int OBJECT_COUNT = 25;
	private static final int OBJECT_SIZE_IN_BYTES = 20;
	private List<byte[]> list = new ArrayList<byte[]>(DEFAULT_CLEAR_PERCENT);
	private AtomicInteger clearPercent = new AtomicInteger(
			DEFAULT_CLEAR_PERCENT);

	private String faultName = null;

	/**
	 * Logger for CartService class
	 */
	private static final Logger log = Logger.getLogger(ServerFaultInjection.class);

	public String getFaultName() {
		return faultName;
	}

	public void setFaultName(String faultName) {
		this.faultName = faultName;
	}

	/**
	 * This method is to inject fault without any params supplied by the user.
	 * 1. causes CPUBurn if faultName specified is CPUBurner
	 * 2. causes memory leak if the faultname specified is memory lak
	 */
	public String injectFault() {


		long startTime = System.currentTimeMillis();
		Random random = new Random();

		/*this is only used to burn the CPU*/

		causeCPUBurn(random.nextInt(1000));
		long endTime = System.currentTimeMillis();
		//return "caused cpu usage and time consumed is:" + (endTime - startTime);

		/*this block is used to cause memory leak on the server*/

		causeMemoryLeak(OBJECT_COUNT, OBJECT_SIZE_IN_BYTES);
		return "consumed cpu and the time taken was " + (endTime - startTime) + " Caused memory leak on the server";
	}

	/**
	 * Causes memory leak on the server
	 * @param objectCount
	 * @param objectSizeInByte
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
	/**
	 * CPU Burner method.
	 * @param n
	 * @return
	 */
	private static long causeCPUBurn(int n) {
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

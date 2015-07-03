package com.appdynamicspilot.faultinjection;

import org.apache.log4j.Logger;

/**
 * Created by shiv.loka on 6/12/15.
 */
public class FaultInjectionFactory {
    
    public FaultInjection getFaultInjection(String faultType){

    	if(faultType.equalsIgnoreCase("memoryleak")){
    		return new MemoryLeakInjection();
    	}
		if(faultType.equalsIgnoreCase("cpuburner")){
			return new CPUBurnerInjection();
		}
    	return null;
    }
}

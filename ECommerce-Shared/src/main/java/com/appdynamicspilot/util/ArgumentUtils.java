package com.appdynamicspilot.util;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;

/**
 * A utility class which checks for null parameters, ranges which would have
 * been passed as arguments to code
 * 
 */
public final class ArgumentUtils {

	public static boolean isNull(final Object argumentValue) {
		return (argumentValue == null);
	}

	public static boolean isNullOrEmpty(final String argumentValue) {
		return (isNull(argumentValue) || StringUtils.isBlank(argumentValue));
	}

	public static boolean isNullOrEmpty(Collection argumentValue) {
		return (isNull(argumentValue) || argumentValue.size() == 0);
	}
	
	public static void checkNull(final String argumentName, final Object argumentValue) {
		if (isNull(argumentValue))
			throw new IllegalArgumentException("The supplied property " + argumentName + " was null");
	}
	
	public static void checkNullOrEmpty(final String argumentName, final String argumentValue) {
		if (isNullOrEmpty(argumentValue))
			throw new IllegalArgumentException("The supplied String property " + argumentName + " was null or empty");
	}

}

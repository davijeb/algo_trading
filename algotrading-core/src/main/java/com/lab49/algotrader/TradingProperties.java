package com.lab49.algotrader;

import java.util.Properties;

/**
 * Properties file to be use for transient variables such as
 * trading volume and property type.
 *
 * @author Jeremy Davies [jerdavies@gmail.com]
 */
public final class TradingProperties extends Properties {

	private static final String PROP_FILE = "/algo.properties";

	// Initialise with the properties in-place
	public static final TradingProperties INSTANCE = new TradingProperties();

	/**
	 * Private constructor which reads the properties file from the classpath location.
	 * If no properties are found it should die.
	 */
	private TradingProperties() {
		try {
			load(TradingProperties.class.getResourceAsStream(PROP_FILE));
		} catch (Exception e) {
			System.out.println("Fatal: Unable to load " + PROP_FILE);
			System.exit(0); // no point continuing

		}
	}

	/**
	 * Get the integer properties value.
	 *
	 * @param key the property key
	 * @return the integer value
	 */
	public int getInteger(String key) {
		return Integer.valueOf(getProperty(key));
	}

	/**
	 * Get the String[] properties value.
	 *
	 * @param key the property key
	 * @return the string array
	 */
	public String[] getStringArray(String key) {
		return getProperty(key).split(",");
	}
}

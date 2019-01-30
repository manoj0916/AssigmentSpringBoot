package com.jlp.application.populator;

/**
 * @author Manoj
 * 
 * Generic interface for populator.
 */
public interface Populator<SOURCE, TARGET> {

	/**
	 * Generic populate method to be overridden for populating data.
	 * @param source
	 * @param target
	 * @param parms
	 */
	void populate(SOURCE source, TARGET target, String... parms);
	
}

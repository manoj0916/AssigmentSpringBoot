package com.jlp.application.populator;

/**
 * @author Manoj
 * 
 * Generic interface for populator.
 */
public interface Populator<SOURCE, TARGET> {

	void populate(SOURCE source, TARGET target, String... parms);
	
}

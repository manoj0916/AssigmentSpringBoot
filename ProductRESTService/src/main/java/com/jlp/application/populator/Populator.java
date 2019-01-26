package com.jlp.application.populator;

/**
 * @author Manoj
 */
public interface Populator<SOURCE, TARGET> {

	void populate(SOURCE source, TARGET target);
	
}

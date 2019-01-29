package com.jlp.application.convertor;

/**
 * @author Manoj
 * 
 * Generic interface for convertor.
 * 
 */
public interface Converter<SOURCE, TARGET> extends org.springframework.core.convert.converter.Converter<SOURCE, TARGET> {

	TARGET convert(SOURCE var1, TARGET var2, String... parms);
}

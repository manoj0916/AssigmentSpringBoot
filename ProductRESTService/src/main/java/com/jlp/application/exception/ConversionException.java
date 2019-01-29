package com.jlp.application.exception;

/**
 * @author Manoj
 *
 */
public class ConversionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ConversionException(Exception e)
	{
		super(e);
	}
	
	public ConversionException(String e)
	{
		super(e);
	}
}

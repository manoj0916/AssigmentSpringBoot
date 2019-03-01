package com.jlp.application.common.exception;

/**
 * @author Manoj
 *
 */
public class RecordProcessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RecordProcessException(Exception e)
	{
		super(e);
	}
	
	public RecordProcessException(String e)
	{
		super(e);
	}
	
	public RecordProcessException(Exception e, String value)
	{
		super(new RecordProcessException(value));
	}
}

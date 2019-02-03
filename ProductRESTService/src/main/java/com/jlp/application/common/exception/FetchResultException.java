package com.jlp.application.common.exception;

public class FetchResultException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FetchResultException(Exception e)
	{
		super(e);
	}
	
	public FetchResultException(String e)
	{
		super(e);
	}
	
	public FetchResultException(Exception e, String value)
	{
		super(new FetchResultException(value));
	}

}

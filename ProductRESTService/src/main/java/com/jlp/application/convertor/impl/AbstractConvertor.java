package com.jlp.application.convertor.impl;

/**
 * @author Manoj
 */
import com.jlp.application.convertor.Converter;
import com.jlp.application.exception.ConversionException;
import com.jlp.application.populator.Populator;

public abstract class AbstractConvertor <SOURCE, TARGET> 
implements Converter<SOURCE, TARGET>, Populator<SOURCE, TARGET>{
	
	private Populator<SOURCE, TARGET> populator;
	private Class<TARGET> targetClass;
	
	@Override
	public TARGET convert(final SOURCE source, final TARGET prototype)
	{
		populate(source, prototype);
		return prototype;
	}

	@Override
	public  void populate(final SOURCE source, final TARGET target)
	{
		populator.populate(source, target);
	}

	@Override
	public TARGET convert(SOURCE source) {
		final TARGET target = targetClass == null ? createTarget() : createFromClass();
		populate(source, target);
		return target;
	}
	
	protected TARGET createFromClass()
	{
		try
		{
			return targetClass.newInstance();
		}
		catch (InstantiationException | IllegalAccessException e)
		{
			throw new ConversionException(e); 
		}
	}
	
	protected TARGET createTarget()
	{
		// optional - no longer requiring sub classes to implement this method
		throw new ConversionException("Not Implemented");
	}
	
	public Populator<SOURCE, TARGET> getPopulator() {
		return populator;
	}

	public void setPopulator(Populator<SOURCE, TARGET> populator) {
		this.populator = populator;
	}

	public Class<TARGET> getTargetClass() {
		return targetClass;
	}

	public void setTargetClass(Class<TARGET> targetClass) {
		this.targetClass = targetClass;
	}
}

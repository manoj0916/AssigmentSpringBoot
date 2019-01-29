package com.jlp.application.util;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.Map;

/**
 * @author Manoj
 */
public class ProductServiceUtil {
	
	private String labelType;

	public String getLabelType() {
		return labelType;
	}

	public void setLabelType(String labelType) {
		this.labelType = labelType;
	}
	
	public String getCurrencyValue(String value, String currencyCode)
	{
		NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
		
		Currency currency = Currency.getInstance(currencyCode);
		format.setCurrency(currency);
		Double doubleValue = Double.parseDouble(value);
		
		if(doubleValue > ApplicationConstant.COMPAREVALUE && doubleValue == Math.floor(doubleValue))
		{
			format.setMaximumFractionDigits(0);
		}
		return format.format(doubleValue);
	}
	
	public String getPercentValue(Double value)
	{
		NumberFormat format = NumberFormat.getPercentInstance(Locale.getDefault());
		
		return format.format(value);
	}
	
	public Double getDoubleValue(String value)
	{
		return Double.parseDouble(value);
	}
	
	public Double substractValues(String value1, String value2)
	{
		return getDoubleValue(value1) - getDoubleValue(value2);
	}
	
	public String getNowPrice(Object price)
	{
		if(price instanceof String)
		{
			return price.toString();
		}else
		{
			return ((Map<String, String>)price).get(ApplicationConstant.TOVALUE);
		}
	}
	
	public Double calculatePercent(String wasPrice, String nowPrice)
	{
		return calculatePercent(getDoubleValue(wasPrice),getDoubleValue(nowPrice));
	}
	
	public Double calculatePercent(Double wasPrice, Double nowPrice)
	{
		return (wasPrice - nowPrice)/wasPrice;
	}
}

package com.jlp.application.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Map;
import java.util.Optional;

/**
 * @author Manoj
 */
public class ProductServiceUtil {

	public String getDecimalValue(String value)
	{
		DecimalFormat decimalFormat = (DecimalFormat)NumberFormat.getInstance();
		Double doubleValue = Double.parseDouble(value);
		if(doubleValue > ApplicationConstant.COMPAREVALUE)
		{
			decimalFormat.applyPattern(ApplicationConstant.BIGVALUEPATTERN);
		}else
		{
			decimalFormat.applyPattern(ApplicationConstant.SMALLVALUEPATTERN);
		}
		return decimalFormat.format(Double.parseDouble(value));
	}
	
	public Double getDoubleValue(String value)
	{
		return Double.parseDouble(value);
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
	
	public String getCurrencySymbol(Map<String, String> currencyMap, String currency)
	{
		return Optional.ofNullable(currencyMap.get(currency.toUpperCase())).orElse(ApplicationConstant.BLANK);
	}
	
	public Double calculatePercent(Double wasPrice, Double nowPrice)
	{
		return (wasPrice - nowPrice)/wasPrice *100;
	}
}

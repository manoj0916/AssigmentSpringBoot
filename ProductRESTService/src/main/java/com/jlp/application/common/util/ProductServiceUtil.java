package com.jlp.application.common.util;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.DefaultMessageSourceResolvable;

/**
 * @author Manoj
 * 
 * Application level utility method are added & maintained in this class.
 * 
 */
public class ProductServiceUtil {

	private MessageSource messageSource;
	
	private Map<String, String> colorRGBMap;
	
	public void setColorRGBMap(Map<String, String> colorRGBMap) {
		this.colorRGBMap = colorRGBMap;
	}


	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	

	/**
	 * Converts values to currency using the currency code & format as per logic.
	 * 
	 * @param value
	 * @param currencyCode
	 * @return
	 */
	public  String getPriceWithCurrency(String value, String currencyCode) {
		NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());

		Currency currency = Currency.getInstance(currencyCode);
		format.setCurrency(currency);
		Double doubleValue = Double.parseDouble(value);

		if (doubleValue > ApplicationConstant.COMPAREVALUE && doubleValue == Math.floor(doubleValue)) {
			format.setMaximumFractionDigits(0);
		}
		return format.format(doubleValue);
	}

	/**
	 * Convert value to its percentage format.
	 * @param value
	 * @return
	 */
	public   String getPercentValue(Double value) {
		return NumberFormat.getPercentInstance(Locale.getDefault()).format(value);
	}

	/**
	 * Convert string value to Double.
	 * @param value
	 * @return
	 */
	public   Double getDoubleValue(String value) {
		return Double.parseDouble(value);
	}

	/**
	 * Subtract values in string after converting to Double.
	 * @param value1
	 * @param value2
	 * @return
	 */
	public  Double subtractValues(String value1, String value2) {
		return getDoubleValue(value1) - getDoubleValue(value2);
	}

	/**
	 * Calculate percent with passed string values after converting to Double.
	 * @param wasPrice
	 * @param nowPrice
	 * @return
	 */
	public   Double calculatePercent(String wasPrice, String nowPrice) {
		return calculatePercent(getDoubleValue(wasPrice), getDoubleValue(nowPrice));
	}

	/**
	 * Calculate percent with passed values.
	 * @param wasPrice
	 * @param nowPrice
	 * @return
	 */
	public static  Double calculatePercent(Double wasPrice, Double nowPrice) {
		return (wasPrice - nowPrice) / wasPrice;
	}

	/**
	 * Get & resolve message using Spring to get final required message by substituting value at proper places.
	 * @param code
	 * @param values
	 * @return
	 */
	public   String getTextMessage(String code, List<Object> values) {
		MessageSourceResolvable dmsgr = new DefaultMessageSourceResolvable(new String[] { code },
				values.toArray(new Object[values.size()]));
		return messageSource.getMessage(dmsgr, Locale.getDefault());
	}
	
	public  String getRGBForBaseColor(String basicColor)
	{
		return Optional.ofNullable(colorRGBMap.get(basicColor.toUpperCase()))
				.orElse(ApplicationConstant.BLANK);
	}

}

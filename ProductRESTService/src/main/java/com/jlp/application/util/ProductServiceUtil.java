package com.jlp.application.util;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.DefaultMessageSourceResolvable;

/**
 * @author Manoj
 */
public class ProductServiceUtil {

	private MessageSource messageSource;

	public String getPriceWithCurrency(String value, String currencyCode) {
		NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());

		Currency currency = Currency.getInstance(currencyCode);
		format.setCurrency(currency);
		Double doubleValue = Double.parseDouble(value);

		if (doubleValue > ApplicationConstant.COMPAREVALUE && doubleValue == Math.floor(doubleValue)) {
			format.setMaximumFractionDigits(0);
		}
		return format.format(doubleValue);
	}

	public String getPercentValue(Double value) {
		return NumberFormat.getPercentInstance(Locale.getDefault()).format(value);
	}

	public Double getDoubleValue(String value) {
		return Double.parseDouble(value);
	}

	public Double substractValues(String value1, String value2) {
		return getDoubleValue(value1) - getDoubleValue(value2);
	}

	public Double calculatePercent(String wasPrice, String nowPrice) {
		return calculatePercent(getDoubleValue(wasPrice), getDoubleValue(nowPrice));
	}

	public Double calculatePercent(Double wasPrice, Double nowPrice) {
		return (wasPrice - nowPrice) / wasPrice;
	}

	public String getTextMessage(String code, List<Object> values) {
		MessageSourceResolvable dmsgr = new DefaultMessageSourceResolvable(new String[] { code },
				values.toArray(new Object[values.size()]));
		return messageSource.getMessage(dmsgr, Locale.getDefault());
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
}

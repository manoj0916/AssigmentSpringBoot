package com.jlp.application.dto;

import java.util.Map;

import com.jlp.application.util.ApplicationConstant;

public class PriceDTO {

	private String was;
	private String then1;
	private String then2;
	private Object now;
	private String uom;
	private String currency;

	public String getWas() {
		return was;
	}

	public void setWas(String was) {
		this.was = was;
	}

	public String getThen1() {
		return then1;
	}

	public void setThen1(String then1) {
		this.then1 = then1;
	}

	public String getThen2() {
		return then2;
	}

	public void setThen2(String then2) {
		this.then2 = then2;
	}

	public String getNow() {

		if (now instanceof String) {
			return now.toString();
		} else {
			return ((Map<?, ?>) now).get(ApplicationConstant.TOVALUE).toString();
		}
	}

	public void setNow(Object now) {
		this.now = now;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
}

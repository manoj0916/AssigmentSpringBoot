package com.jlp.application.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jlp.application.common.util.ApplicationConstant;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Price {

	private String was;
	private String then1;
	private String then2;
	private Object now;
	private String uom;
	private String currency;
	
	public String getNow() {

		if (now instanceof String) {
			return now.toString();
		} else {
			return ((Map<?, ?>) now).get(ApplicationConstant.TOVALUE).toString();
		}
	}

	
}

package com.jlp.application.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.jlp.application.common.util.ApplicationConstant;
import com.jlp.application.common.util.ProductServiceUtil;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "productId", "title", "colorSwatches", "nowPrice", "priceLabel" })
@JsonIgnoreProperties(value={ "type", "code", "price"}, allowGetters= false )

public class Product {
	
	private String productId;
	private String type;
	private String title;
	private List< ColorSwatche > colorSwatches = new ArrayList <> ();
	private String code;
	private Price price;
	@JsonIgnore
	private String labelType;
	@JsonIgnore
	ProductServiceUtil productServiceUtil;
	
	public String getNowPrice()
	{
		return productServiceUtil.getPriceWithCurrency(this.price.getNow(), this.price.getCurrency());
	}
	
	public String getPriceLabel()
	{
		List<Object> priceList = new LinkedList<>();

		String messageLabelCode = ApplicationConstant.SHOWWASNOWMESSAGELABEL;

		if (ApplicationConstant.SHWOPERCENTDISCLABEL.equals(this.labelType)) {
			priceList.add(productServiceUtil
					.getPercentValue(productServiceUtil.calculatePercent(this.price.getWas(), price.getNow())));
			messageLabelCode = ApplicationConstant.SHWOPERCENTDISCMESSAGELABEL;
		} else if (ApplicationConstant.SHOWWASTHENLABEL.equals(this.labelType)
				&& (!StringUtils.isEmpty(this.price.getThen1()) || !StringUtils.isEmpty(this.price.getThen2()))) {
			priceList.add(productServiceUtil.getPriceWithCurrency(this.price.getWas(), this.price.getCurrency()));
			priceList.add(productServiceUtil.getPriceWithCurrency(
					StringUtils.isEmpty(this.price.getThen1()) ? this.price.getThen2() : this.price.getThen1(),
					this.price.getCurrency()));
			messageLabelCode = ApplicationConstant.SHOWWASTHENMESSAGELABEL;
		} else {
			priceList.add(productServiceUtil.getPriceWithCurrency(this.price.getWas(), this.price.getCurrency()));
		}

		priceList.add(this.getNowPrice());

		return productServiceUtil.getTextMessage(messageLabelCode, priceList);
	}
	public List< ColorSwatche > getColorSwatches()
	{
		colorSwatches.forEach(cs -> cs.setRgbColor(Optional.ofNullable(productServiceUtil.getRGBForBaseColor(cs.getBasicColor().toUpperCase()))
				.orElse(ApplicationConstant.BLANK)));
		return colorSwatches;
	}
	
}

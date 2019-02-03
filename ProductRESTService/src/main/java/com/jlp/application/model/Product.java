package com.jlp.application.model;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "productId", "title", "colorSwatches", "nowPrice", "priceLabel" })
@JsonIgnoreProperties(value={ "type", "code", "price"}, allowGetters= false)

public class Product {
	
	private String productId;
	private String type;
	private String title;
	private List< ColorSwatche > colorSwatches = new ArrayList <> ();
	private String code;
	private Price price;
	@JsonIgnoreProperties(allowSetters= false)
	private String nowPrice;
	@JsonIgnoreProperties(allowSetters= false)
	private String priceLabel;
	
}

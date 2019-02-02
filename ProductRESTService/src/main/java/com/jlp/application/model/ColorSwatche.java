package com.jlp.application.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "color", "rgbColor", "skuId"})
@JsonIgnoreProperties(value={ "basicColor", "colorSwatchUrl", "imageUrl", "isAvailable"}, allowGetters= false )
public class ColorSwatche {
	
	private String color;
	private String basicColor;
	private String colorSwatchUrl;
	private String imageUrl;
	private boolean isAvailable;
	@JsonIgnoreProperties(allowSetters= false )
	private String rgbColor;
	private String skuId;
}

package com.jlp.application.dto;

import java.util.ArrayList;
import java.util.List;


public class ProductDTO {
	 private String productId;
	 private String type;
	 private String title;
	 private String code;
	 private PriceDTO price;
	 private List< ColorSwatcheDTO > colorSwatches = new ArrayList <> ();
	 
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public PriceDTO getPrice() {
		return price;
	}
	public void setPrice(PriceDTO price) {
		this.price = price;
	}
	public List<ColorSwatcheDTO> getColorSwatches() {
		return colorSwatches;
	}
	public void setColorSwatches(List<ColorSwatcheDTO> colorSwatches) {
		this.colorSwatches = colorSwatches;
	}
	 
	 }

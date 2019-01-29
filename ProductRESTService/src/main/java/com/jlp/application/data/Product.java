package com.jlp.application.data;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Manoj
 */
public class Product {

	private String productId;
	private String title;
	private String nowPrice;
	private String priceLabel;
	private List<ColorSwatche> colorSwatches = new LinkedList<>();

	public List<ColorSwatche> getColorSwatches() {
		return this.colorSwatches;
	}

	public void setColorSwatches(List<ColorSwatche> colorSwatches) {
		this.colorSwatches = colorSwatches;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNowPrice() {
		return nowPrice;
	}

	public void setNowPrice(String nowPrice) {
		this.nowPrice = nowPrice;
	}

	public String getPriceLabel() {
		return priceLabel;
	}

	public void setPriceLabel(String priceLabel) {
		this.priceLabel = priceLabel;
	}

}

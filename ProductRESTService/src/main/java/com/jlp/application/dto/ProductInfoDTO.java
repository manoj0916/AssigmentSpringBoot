package com.jlp.application.dto;

import java.util.ArrayList;
import java.util.List;

public class ProductInfoDTO {
	private List<ProductDTO> products = new ArrayList<>();

	public List<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}

}

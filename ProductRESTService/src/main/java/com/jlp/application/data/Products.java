package com.jlp.application.data;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Manoj
 */
public class Products {

	private List<Product> products = new LinkedList<>();

	public List<Product> getProducts() {

		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}

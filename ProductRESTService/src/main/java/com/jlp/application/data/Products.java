package com.jlp.application.data;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Manoj
 */
public class Products {

	private List<Product> products;

	public List<Product> getProducts() {
		
		this.products = this.products == null ? new LinkedList<>() : this.products;
		
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}

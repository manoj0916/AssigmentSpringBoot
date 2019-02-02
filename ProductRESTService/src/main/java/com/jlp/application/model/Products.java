package com.jlp.application.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class Products {
	
	@Getter @Setter private List<Product> products = new ArrayList<>();
}

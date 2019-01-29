package com.jlp.application.services;

import java.util.List;

import com.jlp.application.dto.ProductDTO;

/**
 * @author Manoj
 */
public interface ProductInfoService {
	
	List<ProductDTO> getSortedPriceReducedProductsByCategory(String categoryId);

}

package com.jlp.application.services;

import java.util.List;

import com.jlp.application.dto.ProductDTO;

/**
 * @author Manoj
 * 
 * Interface for Product Information service.
 * 
 */
public interface ProductInfoService {
	
	/**
	 * Get product List by category.
	 * @param categoryId
	 * @return
	 */
	List<ProductDTO> getProductsByCategory(String categoryId);
	
	/**
	 * This method returns sorted product list for those price reduced in highest
	 * reduced as first element.
	 * @param categoryId
	 * @return
	 */
	List<ProductDTO> getSortedPriceReducedProductsByCategory(String categoryId);

}

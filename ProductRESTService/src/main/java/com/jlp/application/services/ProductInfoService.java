package com.jlp.application.services;

import com.jlp.application.model.Products;


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
	Products getProductsByCategory(String categoryId);
	
	/**
	 * This method returns sorted product list for those price reduced in highest
	 * reduced as first element.
	 * @param categoryId
	 * @return
	 */
	Products getSortedPriceReducedProductsByCategory(String categoryId, String labelType);

}

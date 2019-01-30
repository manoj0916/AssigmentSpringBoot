package com.jlp.application.facade;


import com.jlp.application.data.Products;

/**
 * @author Manoj
 * 
 * Interface for Product Information facade.
 * 
 */
public interface ProductInfoFacade {
	
	/**
	 * Get product list with reduced price.
	 * @param categoryId
	 * @param labelType
	 * @return
	 */
	Products getReducedPriceProductsByCategory(String categoryId, String labelType);

}

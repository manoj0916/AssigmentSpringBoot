package com.jlp.application.services;

import com.jlp.application.model.Products;

/**
 * @author Manoj
 * 
 * Interface for Web client service.
 * 
 */
public interface WebClientService {

	/**
	 * Get product list by category, calling the API url.
	 * @param categoryId
	 * @return
	 */
	Products getProductListForCategory(String categoryId);
	
}

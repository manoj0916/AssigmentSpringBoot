package com.jlp.application.services;

import com.jlp.application.dto.ProductInfoDTO;


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
	ProductInfoDTO getProductListForCategory(String categoryId);
	
}

package com.jlp.application.services;

import com.jlp.application.dto.ProductInfoDTO;


/**
 * @author Manoj
 * 
 * Interface for Web client service.
 * 
 */
public interface WebClientService {

	ProductInfoDTO getProductListForCategory(String categoryId);
	
}

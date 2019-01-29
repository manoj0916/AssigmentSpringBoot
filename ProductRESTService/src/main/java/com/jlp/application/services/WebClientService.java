package com.jlp.application.services;

import com.jlp.application.dto.ProductInfoDTO;


/**
 * @author Manoj
 */
public interface WebClientService {

	ProductInfoDTO getProductListForCategory(String categoryId);
	
}

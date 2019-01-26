package com.jlp.application.services;

import com.jlp.application.dto.ProductInfoDTO;

/**
 * @author Manoj
 */
public interface ProductInfoService {
	
	ProductInfoDTO getReducedPriceProductsByLabelType(String labelType);

}

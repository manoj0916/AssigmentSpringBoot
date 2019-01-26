package com.jlp.application.services.impl;


import javax.annotation.Resource;

import org.jboss.logging.Logger;

import com.jlp.application.dto.ProductInfoDTO;
import com.jlp.application.services.ProductInfoService;
import com.jlp.application.services.WebClientService;

/**
 * @author Manoj
 */
public class DefaultProductInfoService implements ProductInfoService {

	Logger log = Logger.getLogger(DefaultProductInfoService.class);
	
	@Resource(name="webClientService")
	WebClientService webClientService;
	
	
	
	@Override
	public ProductInfoDTO getReducedPriceProductsByLabelType(String labelType) {
		
		log.debug("::::::::::::::::Inside getReducedPriceProductsByLabelType :::::::::::::::");
		
		ProductInfoDTO productInfoDTO = webClientService.getProductResultFromService();
		productInfoDTO.setLabelType(labelType);
		return productInfoDTO;
	}

}

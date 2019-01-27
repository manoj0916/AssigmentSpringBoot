package com.jlp.application.services.impl;


import org.jboss.logging.Logger;

import com.jlp.application.dto.ProductInfoDTO;
import com.jlp.application.services.ProductInfoService;
import com.jlp.application.services.WebClientService;

/**
 * @author Manoj
 */
public class DefaultProductInfoService implements ProductInfoService {

	private Logger log = Logger.getLogger(DefaultProductInfoService.class);
	
	private WebClientService webClientService;

	@Override
	public ProductInfoDTO getProductsByCategory(String labelType) {
		
		log.debug("::::::::::::::::Inside getReducedPriceProductsByLabelType :::::::::::::::");
		
		ProductInfoDTO productInfoDTO = webClientService.getProductResultFromService();
		productInfoDTO.setLabelType(labelType);
		return productInfoDTO;
	}
	
	public WebClientService getWebClientService() {
		return webClientService;
	}

	public void setWebClientService(WebClientService webClientService) {
		this.webClientService = webClientService;
	}

}

package com.jlp.application.services.impl;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import com.jlp.application.dto.ProductInfoDTO;
import com.jlp.application.services.WebClientService;

/**
 * @author Manoj
 */
public class DefaultWebClientService implements WebClientService {
	
	Logger log= Logger.getLogger(DefaultWebClientService.class);
	
	@Value("${products.source.api.url}")
	private String apiURL;
	
	@Override
	public ProductInfoDTO getProductResultFromService() {
		
		RestTemplate restTemplate = new RestTemplate();
		ProductInfoDTO dto = restTemplate.getForObject(apiURL, ProductInfoDTO.class);
		
		log.debug(":::::::::::::::::: Fetched "+dto.getProducts().size()+" products from API :::::::::::");
		return dto;
	}

}

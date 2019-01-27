package com.jlp.application.services.impl;

import org.jboss.logging.Logger;
import org.springframework.web.client.RestTemplate;

import com.jlp.application.dto.ProductInfoDTO;
import com.jlp.application.services.WebClientService;

/**
 * @author Manoj
 */
public class DefaultWebClientService implements WebClientService {
	
	private Logger log= Logger.getLogger(DefaultWebClientService.class);
	
	private String apiURL;
	
	@Override
	public ProductInfoDTO getProductResultFromService() {
		try
		{
			RestTemplate restTemplate = new RestTemplate();
			ProductInfoDTO dto = restTemplate.getForObject(apiURL, ProductInfoDTO.class);
			log.debug(":::::::::::::::::: Fetched "+dto.getProducts().size()+" products from API :::::::::::");
			return dto;
		}catch (Exception e)
		{
			log.error("Error while fething product list for category::::", e);
			return new ProductInfoDTO();
		}
	}
	
	public String getApiURL() {
		return apiURL;
	}

	public void setApiURL(String apiURL) {
		this.apiURL = apiURL;
	}

}

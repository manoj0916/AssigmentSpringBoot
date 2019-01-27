package com.jlp.application.facade.impl;


import org.jboss.logging.Logger;

import com.jlp.application.convertor.Converter;
import com.jlp.application.data.Products;
import com.jlp.application.dto.ProductInfoDTO;
import com.jlp.application.facade.ProductInfoFacade;
import com.jlp.application.services.ProductInfoService;

/**
 * @author Manoj
 */
public class DefaultProductInfoFacade implements ProductInfoFacade {
	
	private Logger log = Logger.getLogger(DefaultProductInfoFacade.class);
	
	private ProductInfoService productInfoService;
	
	private Converter<ProductInfoDTO,Products> productConvertor;
	
	@Override
	public Products getReducedPriceProductsByLabelType(String labelType) {
		
		log.debug(":::::::::::::::Inside getReducedPriceProductsByLabelType :::::::::::::::::");
		
		return productConvertor.convert(productInfoService.getProductsByCategory(labelType));
	}
	
	public ProductInfoService getProductInfoService() {
		return productInfoService;
	}

	public void setProductInfoService(ProductInfoService productInfoService) {
		this.productInfoService = productInfoService;
	}

	public Converter<ProductInfoDTO, Products> getProductConvertor() {
		return productConvertor;
	}

	public void setProductConvertor(Converter<ProductInfoDTO, Products> productConvertor) {
		this.productConvertor = productConvertor;
	}

}

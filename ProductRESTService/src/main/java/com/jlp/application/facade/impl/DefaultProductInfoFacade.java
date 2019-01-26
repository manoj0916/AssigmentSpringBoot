package com.jlp.application.facade.impl;


import javax.annotation.Resource;

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
	
	Logger log = Logger.getLogger(DefaultProductInfoFacade.class);
	
	@Resource(name="productInfoService")
	ProductInfoService productInfoService;
	
	@Resource(name="productConvertor")
	Converter<ProductInfoDTO,Products> productConvertor;
	
	@Override
	public Products getReducedPriceProductsByLabelType(String labelType) {
		
		log.debug(":::::::::::::::Inside getReducedPriceProductsByLabelType :::::::::::::::::");
		
		return productConvertor.convert(productInfoService.getReducedPriceProductsByLabelType(labelType));
	}

}

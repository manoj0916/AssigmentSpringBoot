package com.jlp.application.facade.impl;


import java.util.List;

import org.jboss.logging.Logger;

import com.jlp.application.convertor.Converter;
import com.jlp.application.data.Products;
import com.jlp.application.dto.ProductDTO;
import com.jlp.application.facade.ProductInfoFacade;
import com.jlp.application.services.ProductInfoService;
import com.jlp.application.util.ProductServiceUtil;

/**
 * @author Manoj
 */
public class DefaultProductInfoFacade implements ProductInfoFacade {
	
	private Logger log = Logger.getLogger(DefaultProductInfoFacade.class);
	
	private ProductInfoService productInfoService;
	
	private Converter<List<ProductDTO>,Products> productConvertor;
	
	private ProductServiceUtil productServiceUtil;

	@Override
	public Products getReducedPriceProductsByCategory(String categoryId, String labelType) {
		
		log.debug(":::::::::::::::Inside getReducedPriceProductsByLabelType :::::::::::::::::("+categoryId+","+labelType+")");
		productServiceUtil.setLabelType(labelType);
		return productConvertor.convert(productInfoService.getSortedPriceReducedProductsByCategory(categoryId));
	}
	
	public void setProductInfoService(ProductInfoService productInfoService) {
		this.productInfoService = productInfoService;
	}

	public void setProductConvertor(Converter<List<ProductDTO>, Products> productConvertor) {
		this.productConvertor = productConvertor;
	}
	
	public void setProductServiceUtil(ProductServiceUtil productServiceUtil) {
		this.productServiceUtil = productServiceUtil;
	}

}

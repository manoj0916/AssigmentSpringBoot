package com.jlp.application.facade.impl;

import java.util.List;

import org.jboss.logging.Logger;

import com.jlp.application.convertor.Converter;
import com.jlp.application.data.Products;
import com.jlp.application.dto.ProductDTO;
import com.jlp.application.facade.ProductInfoFacade;
import com.jlp.application.services.ProductInfoService;

/**
 * @author Manoj
 */
public class DefaultProductInfoFacade implements ProductInfoFacade {

	private Logger log = Logger.getLogger(DefaultProductInfoFacade.class);

	private ProductInfoService productInfoService;

	private Converter<List<ProductDTO>, Products> productConvertor;

	/* (non-Javadoc)
	 * @see com.jlp.application.facade.ProductInfoFacade#getReducedPriceProductsByCategory(java.lang.String, java.lang.String)
	 *
	 * Facade method to get product DTO calling service & convert to final Product POJO using generic populator/converter.
	 * 
	 */
	@Override
	public Products getReducedPriceProductsByCategory(String categoryId, String labelType) {

		log.debug(":::::::::::::::Inside getReducedPriceProductsByLabelType :::::::::::::::::(" + categoryId + ","
				+ labelType + ")");
		return productConvertor.convert(productInfoService.getSortedPriceReducedProductsByCategory(categoryId), new Products(), labelType);
	}

	public void setProductInfoService(ProductInfoService productInfoService) {
		this.productInfoService = productInfoService;
	}

	public void setProductConvertor(Converter<List<ProductDTO>, Products> productConvertor) {
		this.productConvertor = productConvertor;
	}
}

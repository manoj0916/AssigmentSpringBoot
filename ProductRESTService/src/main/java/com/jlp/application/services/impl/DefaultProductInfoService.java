package com.jlp.application.services.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.jboss.logging.Logger;
import org.springframework.util.StringUtils;

import com.jlp.application.common.util.ProductServiceUtil;
import com.jlp.application.model.Product;
import com.jlp.application.model.Products;
import com.jlp.application.services.ProductInfoService;
import com.jlp.application.services.WebClientService;

/**
 * @author Manoj
 * 
 * Default implementation for product information service.
 * 
 */
public class DefaultProductInfoService implements ProductInfoService {

	private Logger log = Logger.getLogger(DefaultProductInfoService.class);

	private WebClientService webClientService;

	private ProductServiceUtil productServiceUtil;

	/*
	 * 
	 * @see com.jlp.application.services.ProductInfoService#
	 * getSortedPriceReducedProductsByCategory(java.lang.String)
	 * 
	 * This method returns sorted product list for those price reduced in highest
	 * reduced as first element.
	 * 
	 */
	@Override
	public Products getSortedPriceReducedProductsByCategory(String categoryId,String labelType) {

		log.debug(":::::::::::::::Inside getProductsByCategory :::::::::::::::::(" + categoryId + ")");

		Products productDTOs = this.getProductsByCategory(categoryId);
		// Filter products with reduced price.
		List<Product> fileredproductDTOs = productDTOs.getProducts().stream().filter(productdto -> reductionFilter(productdto,labelType))
				.collect(Collectors.toList());

		log.debug("::::::::::::::: Populate filterd " + fileredproductDTOs.size() + " products out of "
				+ productDTOs.getProducts().size() + " products :::::::::::::::");
		// Sort products with highest reduced price.
		Collections.sort(fileredproductDTOs,
				(productDTO0, productDTO1) -> comparePriceReduction(productDTO0, productDTO1));
		
		productDTOs.setProducts(fileredproductDTOs);

		return productDTOs;
	}
	
	/* (non-Javadoc)
	 * @see com.jlp.application.services.ProductInfoService#getProductsByCategory(java.lang.String)
	 * 
	 * This method returns product list for the specified category.
	 * 
	 */
	@Override
	public Products getProductsByCategory(String categoryId) {
		return webClientService.getProductListForCategory(categoryId);
	}

	private boolean reductionFilter(Product productdto,String labelType) {
		productdto.setLabelType(labelType);
		productdto.setProductServiceUtil(productServiceUtil);
		return (productdto.getPrice() != null && !StringUtils.isEmpty(productdto.getPrice().getWas())
				&& !StringUtils.isEmpty(productdto.getPrice().getNow()));
	}

	private int comparePriceReduction(Product productDTO0, Product productDTO1) {
		return productServiceUtil.subtractValues(productDTO1.getPrice().getWas(), productDTO1.getPrice().getNow())
				.compareTo(productServiceUtil.subtractValues(productDTO0.getPrice().getWas(),
						productDTO0.getPrice().getNow()));
	}

	public void setWebClientService(WebClientService webClientService) {
		this.webClientService = webClientService;
	}

	public void setProductServiceUtil(ProductServiceUtil productServiceUtil) {
		this.productServiceUtil = productServiceUtil;
	}


}

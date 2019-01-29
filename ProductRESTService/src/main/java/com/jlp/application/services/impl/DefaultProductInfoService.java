package com.jlp.application.services.impl;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.jboss.logging.Logger;
import org.springframework.util.StringUtils;

import com.jlp.application.dto.ProductDTO;
import com.jlp.application.services.ProductInfoService;
import com.jlp.application.services.WebClientService;
import com.jlp.application.util.ProductServiceUtil;

/**
 * @author Manoj
 */
public class DefaultProductInfoService implements ProductInfoService {

	private Logger log = Logger.getLogger(DefaultProductInfoService.class);
	
	private WebClientService webClientService;
	
	private ProductServiceUtil productServiceUtil;

	/* 
	 * This method returns sorted product list for those price reduced in highest reduced as first element.
	 * @see com.jlp.application.services.ProductInfoService#getSortedPriceReducedProductsByCategory(java.lang.String)
	 * 
	 */
	@Override
	public List<ProductDTO> getSortedPriceReducedProductsByCategory(String categoryId) {
		
		log.debug(":::::::::::::::Inside getProductsByCategory :::::::::::::::::("+categoryId+")");
		
		List<ProductDTO> productDTOs = webClientService.getProductListForCategory(categoryId).getProducts();
		//Filter products with reduced price.
		List<ProductDTO> fileredproductDTOs = productDTOs.stream().filter(productdto -> reductionFilter(productdto)).collect(Collectors.toList());
		
		log.debug("::::::::::::::: Populate filterd "+fileredproductDTOs.size()+" products out of "+productDTOs.size()+" products :::::::::::::::");
		//Sort products with highest reduced price.
		Collections.sort(fileredproductDTOs, (productDTO0, productDTO1) -> comparePriceReduction(productDTO0,productDTO1));
		
		return fileredproductDTOs;
	}
	
	private boolean reductionFilter(ProductDTO productdto)
	{
		return (productdto.getPrice()!=null && !StringUtils.isEmpty(productdto.getPrice().getWas()) && !StringUtils.isEmpty(productdto.getPrice().getNow()));
	}
	
	private int comparePriceReduction(ProductDTO productDTO0, ProductDTO productDTO1)
	{
		return productServiceUtil.substractValues(productDTO1.getPrice().getWas(),productServiceUtil.getNowPrice(productDTO1.getPrice().getNow()))
				.compareTo(productServiceUtil.substractValues(productDTO0.getPrice().getWas(),productServiceUtil.getNowPrice(productDTO0.getPrice().getNow())));
	}
	
	public void setWebClientService(WebClientService webClientService) {
		this.webClientService = webClientService;
	}
	
	public void setProductServiceUtil(ProductServiceUtil productServiceUtil) {
		this.productServiceUtil = productServiceUtil;
	}

}

package com.jlp.application.services.impl;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.jboss.logging.Logger;
import org.springframework.util.StringUtils;

import com.jlp.application.common.util.ApplicationConstant;
import com.jlp.application.common.util.ProductServiceUtil;
import com.jlp.application.model.ColorSwatche;
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

	@Resource(name="webClientService")
	private WebClientService webClientService;
	@Resource(name="productServiceUtil")
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

		Products products = this.getProductsByCategory(categoryId);
		// Filter products with reduced price.
		List<Product> fileredproductDTOs = products.getProducts().stream().filter(productdto -> reductionFilter(productdto))
				.collect(Collectors.toList());

		log.debug("::::::::::::::: Populate filterd " + fileredproductDTOs.size() + " products out of "
				+ products.getProducts().size() + " products :::::::::::::::");
		// Sort products with highest reduced price.
		Collections.sort(fileredproductDTOs,
				(productDTO0, productDTO1) -> comparePriceReduction(productDTO0, productDTO1));
		
		products.setProducts(fileredproductDTOs);
		
		populateProductAdditinalInfo(products,labelType);

		return products;
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

	private boolean reductionFilter(Product productdto) {
		return (productdto.getPrice() != null && !StringUtils.isEmpty(productdto.getPrice().getWas())
				&& !StringUtils.isEmpty(productdto.getPrice().getNow()));
	}

	private int comparePriceReduction(Product productDTO0, Product productDTO1) {
		return productServiceUtil.subtractValues(productDTO1.getPrice().getWas(), productDTO1.getPrice().getNow())
				.compareTo(productServiceUtil.subtractValues(productDTO0.getPrice().getWas(),
						productDTO0.getPrice().getNow()));
	}
	
	private void populateProductAdditinalInfo(Products products, String labelType)
	{
		products.getProducts().forEach(product -> {
			
			product.setNowPrice(productServiceUtil.getPriceWithCurrency(product.getPrice().getNow(), product.getPrice().getCurrency()));
			product.setPriceLabel(getPriceLabel(product,labelType));
			setRGBHexaCode(product.getColorSwatches());
		
		});
	}
	private String getPriceLabel(Product product, String labelType)
	{
		List<Object> priceList = new LinkedList<>();

		String messageLabelCode = ApplicationConstant.SHOWWASNOWMESSAGELABEL;

		if (ApplicationConstant.SHWOPERCENTDISCLABEL.equals(labelType)) {
			priceList.add(productServiceUtil
					.getPercentValue(productServiceUtil.calculatePercent(product.getPrice().getWas(), product.getPrice().getNow())));
			messageLabelCode = ApplicationConstant.SHWOPERCENTDISCMESSAGELABEL;
		} else if (ApplicationConstant.SHOWWASTHENLABEL.equals(labelType)
				&& (!StringUtils.isEmpty(product.getPrice().getThen1()) || !StringUtils.isEmpty(product.getPrice().getThen2()))) {
			priceList.add(productServiceUtil.getPriceWithCurrency(product.getPrice().getWas(), product.getPrice().getCurrency()));
			priceList.add(productServiceUtil.getPriceWithCurrency(
					StringUtils.isEmpty(product.getPrice().getThen1()) ? product.getPrice().getThen2() : product.getPrice().getThen1(),
							product.getPrice().getCurrency()));
			messageLabelCode = ApplicationConstant.SHOWWASTHENMESSAGELABEL;
		} else {
			priceList.add(productServiceUtil.getPriceWithCurrency(product.getPrice().getWas(), product.getPrice().getCurrency()));
		}

		priceList.add(product.getNowPrice());
		
		return productServiceUtil.getTextMessage(messageLabelCode, priceList);
	}
	
	private void setRGBHexaCode(List< ColorSwatche > colorSwatches)
	{
		colorSwatches.forEach(cs -> cs.setRgbColor(Optional.ofNullable(productServiceUtil.getRGBForBaseColor(cs.getBasicColor().toUpperCase()))
				.orElse(ApplicationConstant.BLANK)));
	}

}

package com.jlp.application.populator.impl;


import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.util.StringUtils;

import com.jlp.application.data.ColorSwatche;
import com.jlp.application.data.Product;
import com.jlp.application.data.Products;
import com.jlp.application.dto.ColorSwatcheDTO;
import com.jlp.application.dto.PriceDTO;
import com.jlp.application.dto.ProductDTO;
import com.jlp.application.populator.Populator;
import com.jlp.application.util.ApplicationConstant;
import com.jlp.application.util.ProductServiceUtil;

/**
 * @author Manoj
 */
public class ProductInfoPopulator implements Populator<List<ProductDTO>, Products> {
	
	private Logger log = Logger.getLogger(ProductInfoPopulator.class);
	
	private Map<String, String> colorRGBMap;
	
	private ProductServiceUtil productServiceUtil;
	
	private Map<String, String> priceLabelMap;
	
	private MessageSource messageSource;
	
	@Override
	public void populate(List<ProductDTO> source, Products target) {
		
		log.debug("Data population starts for "+source.size()+" products");
		
		source.forEach(productDTO -> {
			Product productInfo = new Product();
			productInfo.setProductId(productDTO.getProductId());
			productInfo.setTitle(productDTO.getTitle());
			populateColorSwatches(productDTO.getColorSwatches(),productInfo);
			populatePrice(productDTO.getPrice(),productInfo);
			populatePriceLabel(productDTO.getPrice(),productInfo);
			target.getProducts().add(productInfo);
		});
		
	}
	
	private void populateColorSwatches(List<ColorSwatcheDTO> colorSwatches, Product product)
	{
		colorSwatches.forEach(colorSwatcheDTO ->
		{
			ColorSwatche colorSwatche=new ColorSwatche();
			colorSwatche.setColor(colorSwatcheDTO.getColor());
			colorSwatche.setSkuid(colorSwatcheDTO.getSkuId());
			colorSwatche.setRgbColor(Optional.ofNullable(colorRGBMap.get(colorSwatcheDTO.getBasicColor().toUpperCase())).orElse(ApplicationConstant.BLANK));
			product.getColorSwatches().add(colorSwatche);
		});
	}
	
	private void populatePrice(PriceDTO priceDTO, Product product)
	{
		
		product.setNowPrice( productServiceUtil.getCurrencyValue(productServiceUtil.getNowPrice(priceDTO.getNow()), priceDTO.getCurrency()));
	}
	
	private void populatePriceLabel(PriceDTO priceDTO, Product product)
	{
		List<Object> priceList = new LinkedList<>();
		String defaultLabelType= productServiceUtil.getLabelType();
		
		boolean setDefaultLabel=true;
		if(defaultLabelType.equals(ApplicationConstant.PERCENTLABEL))
		{
			priceList.add(productServiceUtil.getPercentValue(productServiceUtil.calculatePercent(priceDTO.getWas(), productServiceUtil.getNowPrice(priceDTO.getNow()))));
		}else
		{
			priceList.add(productServiceUtil.getCurrencyValue(priceDTO.getWas(),priceDTO.getCurrency()));
		}
		
		if(defaultLabelType.equals(ApplicationConstant.SHOWTHENLABEL) && (!StringUtils.isEmpty(priceDTO.getThen1()) || !StringUtils.isEmpty(priceDTO.getThen2())))
		{
			priceList.add(productServiceUtil.getCurrencyValue(StringUtils.isEmpty(priceDTO.getThen1())?priceDTO.getThen2():priceDTO.getThen1(),priceDTO.getCurrency()));
			setDefaultLabel=false;
		}
		
		if(!priceList.isEmpty())
		{
			priceList.add(product.getNowPrice());
		}
		
		if(setDefaultLabel && defaultLabelType.equals(ApplicationConstant.SHOWTHENLABEL))
		{
			defaultLabelType= ApplicationConstant.SHOWNOWLABEL;
		}
		MessageSourceResolvable dmsgr= new DefaultMessageSourceResolvable(new String[]{priceLabelMap.get(defaultLabelType)}, priceList.toArray(new Object[priceList.size()]) );
	
		product.setPriceLabel(messageSource.getMessage(dmsgr, Locale.getDefault()));
	
	}

	public void setColorRGBMap(Map<String, String> colorRGBMap) {
		this.colorRGBMap = colorRGBMap;
	}

	public void setProductServiceUtil(ProductServiceUtil productServiceUtil) {
		this.productServiceUtil = productServiceUtil;
	}
	
	public void setPriceLabelMap(Map<String, String> priceLabelMap) {
		this.priceLabelMap = priceLabelMap;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

}

package com.jlp.application.populator.impl;


import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jboss.logging.Logger;
import org.springframework.util.StringUtils;

import com.jlp.application.data.ColorSwatche;
import com.jlp.application.data.Product;
import com.jlp.application.data.Products;
import com.jlp.application.dto.ColorSwatcheDTO;
import com.jlp.application.dto.PriceDTO;
import com.jlp.application.dto.ProductDTO;
import com.jlp.application.dto.ProductInfoDTO;
import com.jlp.application.populator.Populator;
import com.jlp.application.util.ApplicationConstant;
import com.jlp.application.util.ProductServiceUtil;

/**
 * @author Manoj
 */
public class ProductInfoPopulator implements Populator<ProductInfoDTO, Products> {
	
	private Logger log = Logger.getLogger(ProductInfoPopulator.class);
	
	private Map<String, String> colorRGBMap;
	
	private Map<String, String> currencyMap;
	
	private ProductServiceUtil productServiceUtil;
	
	private String[] priceLabels;

	private LinkedList<String> priceLabelList;

	@Override
	public void populate(ProductInfoDTO source, Products target) {
		
		priceLabelList = new LinkedList <> (Arrays.asList(getPriceLabels()));

		List<ProductDTO> productDTOs = source.getProducts();
		//Filter products with reduced price.
		List<ProductDTO> fileredproductDTOs = productDTOs.stream().filter(productdto -> reductionFilter(productdto)).collect(Collectors.toList());
		
		log.debug("::::::::::::::: Populate filterd "+fileredproductDTOs.size()+" products out of "+source.getProducts().size()+" products :::::::::::::::");
		//Sort products with highest reduced price.
		Collections.sort(fileredproductDTOs, (productDTO0, productDTO1) -> comparePriceReduction(productDTO0,productDTO1));
		
		fileredproductDTOs.forEach(productDTO -> {
			Product productInfo = new Product();
			productInfo.setProductId(productDTO.getProductId());
			productInfo.setTitle(productDTO.getTitle());
			populateColorSwatches(productDTO.getColorSwatches(),productInfo);
			populatePrice(productDTO.getPrice(),productInfo);
			populatePriceLabel(source,productDTO.getPrice(),productInfo);
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
			colorSwatche.setRgbColor(Optional.ofNullable(colorRGBMap.get(colorSwatcheDTO.getBasicColor().toUpperCase())).orElse(""));
			product.getColorSwatches().add(colorSwatche);
		});
	}
	
	private void populatePrice(PriceDTO priceDTO, Product product)
	{
		
		product.setNowPrice(productServiceUtil.getCurrencySymbol(currencyMap, priceDTO.getCurrency()) + productServiceUtil.getDecimalValue(productServiceUtil.getNowPrice(priceDTO.getNow())));
	}
	
	private boolean reductionFilter(ProductDTO productdto)
	{
		return (!StringUtils.isEmpty(productdto.getPrice().getWas()) && !StringUtils.isEmpty(productdto.getPrice().getNow()));
	}
	
	private int comparePriceReduction(ProductDTO productDTO0, ProductDTO productDTO1)
	{
		Double value1 = productServiceUtil.getDoubleValue(productDTO0.getPrice().getWas()) - productServiceUtil.getDoubleValue(productServiceUtil.getNowPrice(productDTO0.getPrice().getNow()));
		Double value2 = (productServiceUtil.getDoubleValue(productDTO1.getPrice().getWas()) - productServiceUtil.getDoubleValue(productServiceUtil.getNowPrice(productDTO1.getPrice().getNow())));
		return value2.compareTo(value1);
	}
	
	private void populatePriceLabel(ProductInfoDTO source,PriceDTO priceDTO, Product product)
	{
		List<String> filteredPriceLabelList = priceLabelList.stream().filter(label -> labelFilter(label,source.getLabelType())).collect(Collectors.toList());
		
		String currency = productServiceUtil.getCurrencySymbol(currencyMap, priceDTO.getCurrency());
		
		prepareFinalPriceLabel(filteredPriceLabelList,currency,priceDTO, product);
		
	}
	
	private boolean labelFilter(String priceLabel, String labelType)
	{
		return ((ApplicationConstant.SHOWNOWLABEL.equals(labelType) || ApplicationConstant.SHOWTHENLABEL.equals(labelType)) && labelType.toLowerCase().contains(priceLabel.toLowerCase().trim())) || (ApplicationConstant.PERCENTLABEL.equals(labelType) && priceLabel.contains(ApplicationConstant.PERCENT));
	}
	
	private void prepareFinalPriceLabel(List<String> filteredPriceLabelList, String currency,PriceDTO priceDTO, Product product)
	{
		List<String> resultPriceLabelList = new LinkedList<>();
		StringBuilder identity = new StringBuilder(ApplicationConstant.BLANK);
		filteredPriceLabelList.forEach(priceLabel ->
		{
			if(priceLabel.contains(ApplicationConstant.WASVALUEPATTERN))
			{
				resultPriceLabelList.add(priceLabel);
				resultPriceLabelList.add(ApplicationConstant.SPACE);
				resultPriceLabelList.add(currency);
				resultPriceLabelList.add(productServiceUtil.getDecimalValue(priceDTO.getWas()));
				resultPriceLabelList.add(ApplicationConstant.COMMA);
			}else if(priceLabel.contains(ApplicationConstant.THENVALUEPATTERN) && (!StringUtils.isEmpty(priceDTO.getThen1()) || !StringUtils.isEmpty(priceDTO.getThen2())))
			{
				resultPriceLabelList.add(ApplicationConstant.SPACE);
				resultPriceLabelList.add(priceLabel);
				resultPriceLabelList.add(ApplicationConstant.SPACE);
				resultPriceLabelList.add(currency);
				resultPriceLabelList.add(productServiceUtil.getDecimalValue(StringUtils.isEmpty(priceDTO.getThen1())?priceDTO.getThen2():priceDTO.getThen1()));
				resultPriceLabelList.add(ApplicationConstant.COMMA);
			}else if(priceLabel.contains(ApplicationConstant.PERCENT))
			{
				identity.append(productServiceUtil.getDecimalValue(productServiceUtil.calculatePercent(productServiceUtil.getDoubleValue(priceDTO.getWas()), productServiceUtil.getDoubleValue(productServiceUtil.getNowPrice(priceDTO.getNow()))).toString()));
				resultPriceLabelList.add(priceLabel);
				resultPriceLabelList.add(ApplicationConstant.SPACE);
			}else if(priceLabel.contains(ApplicationConstant.NOWVALUEPATTERN))
			{
				resultPriceLabelList.add(ApplicationConstant.SPACE);
				resultPriceLabelList.add(priceLabel);
				resultPriceLabelList.add(ApplicationConstant.SPACE);
			}
		});
		if(!resultPriceLabelList.isEmpty())
		{
			resultPriceLabelList.add(product.getNowPrice());
		}
		product.setPriceLabel(resultPriceLabelList.stream().reduce(identity.toString(), (a,b) -> a + b));
	}
	
	public Map<String, String> getColorRGBMap() {
		return colorRGBMap;
	}

	public void setColorRGBMap(Map<String, String> colorRGBMap) {
		this.colorRGBMap = colorRGBMap;
	}

	public Map<String, String> getCurrencyMap() {
		return currencyMap;
	}

	public void setCurrencyMap(Map<String, String> currencyMap) {
		this.currencyMap = currencyMap;
	}

	public ProductServiceUtil getProductServiceUtil() {
		return productServiceUtil;
	}

	public void setProductServiceUtil(ProductServiceUtil productServiceUtil) {
		this.productServiceUtil = productServiceUtil;
	}

	public String[] getPriceLabels() {
		return priceLabels;
	}

	public void setPriceLabels(String[] priceLabels) {
		this.priceLabels = priceLabels;
	}
}

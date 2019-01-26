package com.jlp.application.populator.impl;


import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

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
	
	Logger log = Logger.getLogger(ProductInfoPopulator.class);
	
	@Resource(name="colorRGBMap")
	Map<String, String> colorRGBMap;
	
	@Resource(name="currencyMap")
	Map<String, String> currencyMap;
	
	@Resource(name="productServiceUtil")
	ProductServiceUtil productServiceUtil;
	
	LinkedList<String> priceLabelList = new LinkedList <> (Arrays.asList(ApplicationConstant.PRICELABELLIST));

	@Override
	public void populate(ProductInfoDTO source, Products target) {

		List<ProductDTO> productDTOs = source.getProducts();
		
		List<ProductDTO> fileredproductDTOs = productDTOs.stream().filter(productdto -> reductionFilter(productdto)).collect(Collectors.toList());
		
		log.debug("::::::::::::::: Populate filterd "+fileredproductDTOs.size()+" products out of "+source.getProducts().size()+" products :::::::::::::::");

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
		return labelType.toLowerCase().contains(priceLabel.toLowerCase().trim()) || (ApplicationConstant.PERCENTLABEL.equals(labelType) && priceLabel.contains(ApplicationConstant.PERCENT));
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
				resultPriceLabelList.add(currency+productServiceUtil.getDecimalValue(priceDTO.getWas())+ ApplicationConstant.COMMA);
			}else if(priceLabel.contains(ApplicationConstant.THENVALUEPATTERN) && (!StringUtils.isEmpty(priceDTO.getThen1()) || !StringUtils.isEmpty(priceDTO.getThen2())))
			{
				resultPriceLabelList.add(priceLabel);
				resultPriceLabelList.add(currency+productServiceUtil.getDecimalValue(StringUtils.isEmpty(priceDTO.getThen1())?priceDTO.getThen2():priceDTO.getThen1())+ ApplicationConstant.COMMA);
			}else if(priceLabel.contains(ApplicationConstant.PERCENT))
			{
				identity.append(productServiceUtil.getDecimalValue(productServiceUtil.calculatePercent(productServiceUtil.getDoubleValue(priceDTO.getWas()), productServiceUtil.getDoubleValue(productServiceUtil.getNowPrice(priceDTO.getNow()))).toString()));
				resultPriceLabelList.add(priceLabel);
			}else if(priceLabel.contains(ApplicationConstant.NOWVALUEPATTERN))
			{
				resultPriceLabelList.add(priceLabel);
			}
		});
		if(!resultPriceLabelList.isEmpty())
		{
			resultPriceLabelList.add(product.getNowPrice());
		}
		product.setPriceLabel(resultPriceLabelList.stream().reduce(identity.toString(), (a,b) -> a + b));
	}
}

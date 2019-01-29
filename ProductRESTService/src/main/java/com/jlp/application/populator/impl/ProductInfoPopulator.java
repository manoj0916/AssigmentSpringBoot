package com.jlp.application.populator.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.jboss.logging.Logger;
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
 * @author Manoj Populator which will populate list of products from DTOs
 *         received from service.
 */
public class ProductInfoPopulator implements Populator<List<ProductDTO>, Products> {

	private Logger log = Logger.getLogger(ProductInfoPopulator.class);

	private Map<String, String> colorRGBMap;

	private ProductServiceUtil productServiceUtil;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jlp.application.populator.Populator#populate(java.lang.Object,
	 * java.lang.Object, java.lang.String[]) Generic populator to populate target
	 * from source.
	 */
	@Override
	public void populate(List<ProductDTO> source, Products target, String... parms) {
		log.debug("Data population starts for " + source.size() + " products");
		source.forEach(productDTO -> {
			Product productInfo = new Product();
			productInfo.setProductId(productDTO.getProductId());
			productInfo.setTitle(productDTO.getTitle());
			populateColorSwatches(productDTO.getColorSwatches(), productInfo);
			populatePrice(productDTO.getPrice(), productInfo);
			populatePriceLabel(productDTO.getPrice(), productInfo,
					parms == null ? new String[] { ApplicationConstant.SHOWWASNOWLABEL } : parms);
			target.getProducts().add(productInfo);
		});

	}

	/**
	 * Populate ColorSwatches to target from source.
	 * 
	 * @param colorSwatches
	 * @param product
	 */
	private void populateColorSwatches(List<ColorSwatcheDTO> colorSwatches, Product product) {
		colorSwatches.forEach(colorSwatcheDTO -> {
			ColorSwatche colorSwatche = new ColorSwatche();
			colorSwatche.setColor(colorSwatcheDTO.getColor());
			colorSwatche.setSkuid(colorSwatcheDTO.getSkuId());
			colorSwatche.setRgbColor(Optional.ofNullable(colorRGBMap.get(colorSwatcheDTO.getBasicColor().toUpperCase()))
					.orElse(ApplicationConstant.BLANK));
			product.getColorSwatches().add(colorSwatche);
		});
	}

	/**
	 * Populate Now price for the product.
	 * 
	 * @param priceDTO
	 * @param product
	 */
	private void populatePrice(PriceDTO priceDTO, Product product) {

		product.setNowPrice(productServiceUtil.getPriceWithCurrency(priceDTO.getNow(), priceDTO.getCurrency()));
	}

	/**
	 * Populate price label for the target as per passed params when requested.
	 * 
	 * @param priceDTO
	 * @param product
	 * @param params
	 */
	private void populatePriceLabel(PriceDTO priceDTO, Product product, String[] params) {

		List<Object> priceList = new LinkedList<>();

		String messageLabelCode = ApplicationConstant.SHOWWASNOWMESSAGELABEL;

		if (params[0].equals(ApplicationConstant.SHWOPERCENTDISCLABEL)) {
			priceList.add(productServiceUtil
					.getPercentValue(productServiceUtil.calculatePercent(priceDTO.getWas(), priceDTO.getNow())));
			messageLabelCode = ApplicationConstant.SHWOPERCENTDISCMESSAGELABEL;
		} else {
			priceList.add(productServiceUtil.getPriceWithCurrency(priceDTO.getWas(), priceDTO.getCurrency()));
		}

		if (params[0].equals(ApplicationConstant.SHOWWASTHENLABEL)
				&& (!StringUtils.isEmpty(priceDTO.getThen1()) || !StringUtils.isEmpty(priceDTO.getThen2()))) {
			priceList.add(productServiceUtil.getPriceWithCurrency(
					StringUtils.isEmpty(priceDTO.getThen1()) ? priceDTO.getThen2() : priceDTO.getThen1(),
					priceDTO.getCurrency()));
			messageLabelCode = ApplicationConstant.SHOWWASTHENMESSAGELABEL;
		}

		if (!priceList.isEmpty()) {
			priceList.add(product.getNowPrice());
		}

		product.setPriceLabel(productServiceUtil.getTextMessage(messageLabelCode, priceList));
	}

	/**
	 * @param colorRGBMap
	 */
	public void setColorRGBMap(Map<String, String> colorRGBMap) {
		this.colorRGBMap = colorRGBMap;
	}

	/**
	 * @param productServiceUtil
	 */
	public void setProductServiceUtil(ProductServiceUtil productServiceUtil) {
		this.productServiceUtil = productServiceUtil;
	}
}

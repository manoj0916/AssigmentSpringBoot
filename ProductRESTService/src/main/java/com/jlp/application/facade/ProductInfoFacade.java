package com.jlp.application.facade;


import com.jlp.application.data.Products;

/**
 * @author Manoj
 */
public interface ProductInfoFacade {
	
	Products getReducedPriceProductsByLabelType(String labelType);

}

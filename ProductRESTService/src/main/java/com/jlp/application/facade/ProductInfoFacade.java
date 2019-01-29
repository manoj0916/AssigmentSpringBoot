package com.jlp.application.facade;


import com.jlp.application.data.Products;

/**
 * @author Manoj
 * 
 * Interface for Product Information facade.
 * 
 */
public interface ProductInfoFacade {
	
	Products getReducedPriceProductsByCategory(String categoryId, String labelType);

}

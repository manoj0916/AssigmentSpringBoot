package com.jlp.application.webservices;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jlp.application.data.Products;
import com.jlp.application.facade.ProductInfoFacade;

/**
 * @author Manoj
 */

@RestController
public class ProductInfoRESTService {
	
	@Resource(name="productInfoFacade")
	ProductInfoFacade productInfoFacade;
	
    @RequestMapping("/products")
    public Products getProducts(@RequestParam(value="labelType", defaultValue="ShowWasNow") String labelType) {
    	
        return productInfoFacade.getReducedPriceProductsByLabelType(labelType);
    }

}

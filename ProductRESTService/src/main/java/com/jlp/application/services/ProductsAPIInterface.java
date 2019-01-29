package com.jlp.application.services;

import com.jlp.application.dto.ProductInfoDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author Manoj
 *
 * Interface to set path and value parameters to REST services.
 * 
 */
public interface ProductsAPIInterface {
	
	@GET("v1/categories/{category}/products")
    Call<ProductInfoDTO> getProductByCategory(@Path("category") String categoryId,@Query("key") String key);
}

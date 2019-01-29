package com.jlp.application.services.impl;

import java.time.Duration;
import org.jboss.logging.Logger;

import com.jlp.application.dto.ProductInfoDTO;
import com.jlp.application.services.ProductsAPIInterface;
import com.jlp.application.services.WebClientService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Manoj
 * 
 * Default implementation for web client service.
 * 
 */
public class DefaultWebClientService implements WebClientService {

	private Logger log = Logger.getLogger(DefaultWebClientService.class);

	private String apiBaseURL;

	private String key;

	private OkHttpClient okHttpClient;

	public void setKey(String key) {
		this.key = key;
	}

	public void setApiBaseURL(String apiBaseURL) {
		this.apiBaseURL = apiBaseURL;
	}

	/**
	 * @param okHttpClient
	 * @param params
	 * Constructor which set connect & read timeout to okHttpClient.
	 */
	public DefaultWebClientService(OkHttpClient okHttpClient, String[] params) {
		super();
		okHttpClient.newBuilder().connectTimeout(Duration.ofMillis(Long.parseLong(params[0])))
				.readTimeout(Duration.ofMillis(Long.parseLong(params[1])));
		this.okHttpClient = okHttpClient;
	}

	/**
	 * Generic method which get list of all products for the passed category
	 * 
	 * No specific logic for filter added here to enable this methods to get all
	 * products for a defined category. This will make this method re-usable for
	 * other purposes.
	 * 
	 * @param categoryId
	 * 
	 */
	@Override
	public ProductInfoDTO getProductListForCategory(String categoryId) {
		try {
			Retrofit retrofit = new Retrofit.Builder().baseUrl(apiBaseURL).client(okHttpClient)
					.addConverterFactory(GsonConverterFactory.create()).build();

			return retrofit.create(ProductsAPIInterface.class).getProductByCategory(categoryId, key).execute().body();

		} catch (Exception e) {
			log.error("Error while fething product list for category::::", e);
			return new ProductInfoDTO();
		}
	}

}

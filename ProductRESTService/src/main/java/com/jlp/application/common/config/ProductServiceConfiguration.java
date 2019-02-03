package com.jlp.application.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.jlp.application.common.util.ProductServiceUtil;
import com.jlp.application.services.ProductInfoService;
import com.jlp.application.services.WebClientService;
import com.jlp.application.services.impl.DefaultProductInfoService;
import com.jlp.application.services.impl.DefaultWebClientService;

import okhttp3.OkHttpClient;

/**
 * @author Manoj
 * 
 * Spring configuration class to related configuration for Spring Boot.
 * 
 */

@Configuration
@ComponentScan("com.jlp.application")
@PropertySource("classpath:resources/application.properties")
public class ProductServiceConfiguration {
	
	@Value("${products.category.api.connection.timeout},${products.category.api.read.timeout}")
	private String[] okHTTPParams;
	
	@Bean
	public ProductInfoService productInfoService()
	{
		return new DefaultProductInfoService();
	}
	@Bean
	public ProductServiceUtil productServiceUtil()
	{
		return new ProductServiceUtil();
	}
	@Bean
	public WebClientService webClientService()
	{
		return new DefaultWebClientService(okHttpClient(),okHTTPParams);
	}
	
	@Bean
	public OkHttpClient okHttpClient()
	{
		return new OkHttpClient();
	}
	@Bean
	public MessageSource messageSource()
	{
		 ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
         messageSource.setBasename("resources/messages");
         return messageSource;
	}
}

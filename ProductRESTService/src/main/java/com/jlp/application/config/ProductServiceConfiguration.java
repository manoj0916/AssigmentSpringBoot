package com.jlp.application.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * @author Manoj
 */

@Configuration
@PropertySource("classpath:resources/application.properties")
@ImportResource("classpath:resources/applicationContext.xml")
public class ProductServiceConfiguration {
	/*@Bean
	public MessageSource messageSource() {
	    ReloadableResourceBundleMessageSource messageSource
	      = new ReloadableResourceBundleMessageSource();
	     
	    messageSource.setBasename("classpath:messages/messages");
	    messageSource.setDefaultEncoding("UTF-8");
	    return messageSource;
	}*/
}

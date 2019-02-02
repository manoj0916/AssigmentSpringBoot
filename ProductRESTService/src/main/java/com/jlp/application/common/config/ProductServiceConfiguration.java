package com.jlp.application.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Manoj
 * 
 * Spring configuration class to related configuration for Spring Boot.
 * 
 */

@Configuration
@PropertySource("classpath:resources/application.properties")
@ImportResource("classpath:resources/applicationContext.xml")
public class ProductServiceConfiguration {
}

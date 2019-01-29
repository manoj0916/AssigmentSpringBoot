package com.jlp.application.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Manoj
 */

@Configuration
@PropertySource("classpath:resources/application.properties")
@ImportResource("classpath:resources/applicationContext.xml")
public class ProductServiceConfiguration {
}

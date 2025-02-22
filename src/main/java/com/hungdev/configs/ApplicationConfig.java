package com.hungdev.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "com.hungdev")
@PropertySource(value = "classpath:application.properties")
public class ApplicationConfig {
	
}

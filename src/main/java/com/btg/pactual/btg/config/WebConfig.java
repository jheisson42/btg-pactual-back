package com.btg.pactual.btg.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

	   @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**")
	            .allowedOrigins("https://angular-btg-pactual.s3.us-east-2.amazonaws.com")
	            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") 
	            .allowedHeaders("*")
	            .allowCredentials(true);
	    }
}

package com.lagoon.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan(basePackages = { "com.lagoon" }) //temporary
@EnableWebMvc
public class RestConfig extends WebMvcConfigurerAdapter  {

	   @Override
	    public void addCorsMappings(CorsRegistry corsRegistry) {
	        corsRegistry.addMapping( "/**" )
	                .allowedOrigins( "*" )
	                .allowedMethods( "GET", "POST", "DELETE" )
	                .allowedHeaders( "*" )
	                .allowCredentials( true )
	                .exposedHeaders( "Authorization" )
	                .maxAge( 3600 );
	    }
}
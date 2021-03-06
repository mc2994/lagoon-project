package com.lagoon.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class RestConfig extends WebMvcConfigurerAdapter  {

	   @Override
	    public void addCorsMappings(CorsRegistry corsRegistry) {
	        corsRegistry.addMapping( "/**" )
	                .allowedOrigins( "*" )
	                .allowedMethods( "GET", "POST", "DELETE" )
	                .allowedHeaders( "*" )
	                .allowCredentials( true )
	                .exposedHeaders( "Authorization","content-disposition" )
	                .maxAge( 3600 );
	    }
}
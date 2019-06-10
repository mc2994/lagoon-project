package com.lagoon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.lagoon.config.JwtFilter;

@SpringBootApplication
public class LagoonBackendApplication {

	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/rest/*");
		return registrationBean;
	}
	
	  @Bean
	   public RestTemplate getRestTemplate() {
	      return new RestTemplate();
	   }

	public static void main(String[] args) {
		SpringApplication.run(LagoonBackendApplication.class, args);
	}
}
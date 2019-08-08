package com.lagoon;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import com.lagoon.config.JwtFilter;

@EnableAsync
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
	  
//	    @Bean(name = "asyncExecutor")
//	    public Executor asyncExecutor() {
//	        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//	        executor.setCorePoolSize(3);
//	        executor.setMaxPoolSize(3);
//	        executor.setQueueCapacity(100);
//	        executor.setThreadNamePrefix("AsynchThread-");
//	        executor.initialize();
//	        return executor;
//	    }

	public static void main(String[] args) {
		SpringApplication.run(LagoonBackendApplication.class, args);
	}
}
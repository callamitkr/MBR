package com.capgemini.mbr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.capgemini.mbr.filter.SimpleFilter;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
//@EnableOAuth2Sso
public class MbrtGatwayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MbrtGatwayApplication.class, args);
	}
	 @Bean
	  public SimpleFilter simpleFilter() {
	    return new SimpleFilter();
	  }
	
	
}

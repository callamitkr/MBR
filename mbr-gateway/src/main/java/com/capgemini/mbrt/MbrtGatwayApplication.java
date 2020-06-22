package com.capgemini.mbrt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
//@EnableOAuth2Sso
public class MbrtGatwayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MbrtGatwayApplication.class, args);
	}
	
	
}

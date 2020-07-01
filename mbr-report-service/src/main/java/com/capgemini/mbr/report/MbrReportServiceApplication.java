package com.capgemini.mbr.report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.capgemini.mbr.report.util.DateUtil;



@SpringBootApplication
@EnableAspectJAutoProxy
@EnableDiscoveryClient
public class MbrReportServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MbrReportServiceApplication.class, args);
	}

	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource
				= new ReloadableResourceBundleMessageSource();

		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public LocalValidatorFactoryBean getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}
	@Bean
	public DateUtil getDateUtil(){
		DateUtil dateUtil = new DateUtil();
		return dateUtil;
	}
}

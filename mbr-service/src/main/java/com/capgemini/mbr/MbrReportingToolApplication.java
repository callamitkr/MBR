package com.capgemini.mbr;

import com.capgemini.mbr.model.Report;
import com.capgemini.mbr.repository.ReportRepository;
import com.capgemini.mbr.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableDiscoveryClient
public class MbrReportingToolApplication implements CommandLineRunner {
	@Autowired
	ReportRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(MbrReportingToolApplication.class, args);
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
    @Override
	public void run(String... args) throws Exception {
		List<Report> reports = Arrays.asList(
				new Report(123L, "Card control1", "card control desc1", "barclays1", "Bhavesh Shukla", "phase1",
						"Green", "report generation", "good", "no issue","amit", LocalDate.now(),LocalDate.now()),
		 		new Report(124L, "Card control2", "card control desc2", "barclays2", "Bhavesh Shukla", "phase1",
				"Red", "report generation", "good", "no able to deliver full","akuma397",LocalDate.now(),LocalDate.now()),
		 		new Report(125L, "Card control3", "card control desc3", "barclays3", "Bhavesh Shukla", "phase1",
				"Amber", "report generation", "good", "Having issue for this sprint","arnav",LocalDate.now(),LocalDate.now()));
		repository.saveAll(reports);
	}
}

package com.capgemini.mbr;

import com.capgemini.mbr.model.*;
import com.capgemini.mbr.repository.*;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Locale;

//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableAspectJAutoProxy
//@EnableDiscoveryClient
public class MbrReportingToolApplication implements CommandLineRunner  {
	@Autowired
	ReportRepository reportRepository;
	@Autowired
	BuRepository buRepository;
	@Autowired
	PhaseRepository phaseRepository;
	@Autowired
	ProjectRepository projectsRepository;
	@Autowired
	ProjectStatusRepository projectStatusRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(MbrReportingToolApplication.class, args);
	}


	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(Locale.US);
		return sessionLocaleResolver;
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		messageSource.setUseCodeAsDefaultMessage(true);
		return messageSource;
	}

	@Bean
	public LocalValidatorFactoryBean getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}

	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server inMemoryH2DatabaseaServer() throws SQLException {
	    return Server.createTcpServer(
	      "-tcp", "-tcpAllowOthers", "-tcpPort", "9090");
	}

    @Override
	public void run(String... args) throws Exception {

    	Phase phase1 = new Phase("Development");
    	Phase phase2 = new Phase("Support");
    	Phase phase3 = new Phase("Maintenance");
    	Phase phase4 = new Phase("Production Support");
    	
    	Bu bu1 = new Bu("US");
    	Bu bu2 = new Bu("UK");
    	Bu bu3 = new Bu("Canada");
    	Project project1 =  new Project("Cyber Security","This for cyber Security Project","Ajay singh");
    	Project project2 = new Project("Push Notification","This project for push notification for US Client","Bhavesh Shukla");
    	Project project3 =	new Project("Easy pay","It is for Payment","Amit Rai");
	
    	project1.setBu(bu1);
    	project2.setBu(bu2);
    	project3.setBu(bu3);
    	
		project1.setPhase(phase1);
		project2.setPhase(phase1);
		project3.setPhase(phase3);
		 
		buRepository.save(bu1);
	    buRepository.save(bu2); 
	    buRepository.save(bu3); 
		 
		phaseRepository.save(phase1);
		phaseRepository.save(phase2);
		phaseRepository.save(phase3);
		 
		projectsRepository.save(project1);
		projectsRepository.save(project2);
		projectsRepository.save(project3);
		 
		ProjectStatus projectStatus1 = new ProjectStatus("Green");
	    ProjectStatus projectStatus2 = new ProjectStatus("Amber");
	    ProjectStatus projectStatus3 = new ProjectStatus("Red");
    	projectStatusRepository.save(projectStatus1);
    	projectStatusRepository.save(projectStatus2);
    	projectStatusRepository.save(projectStatus3);
    	
    	Report report1 = new Report("dev completed", "Have delivery issue","Till now no issue","amit",LocalDate.now(),LocalDate.now());
    	Report report2 = new Report("in development", "No Issue","N/A","akuma",LocalDate.now(),LocalDate.now());
    	Report report3 = new Report("UAT Completed", "Good","no issue","akuma397",LocalDate.now(),LocalDate.now());
    	
    	report2.setProjectStatus(projectStatus2);
    	report3.setProjectStatus(projectStatus3);
    	report1.setProjectStatus(projectStatus1);

    	report1.setProject(project1);
    	report2.setProject(project2);
    	report3.setProject(project3);

    	reportRepository.save(report1);
    	reportRepository.save(report2);
    	reportRepository.save(report3);

	}
}

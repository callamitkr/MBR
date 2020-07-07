package com.capgemini.mbr.report.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.capgemini.mbr.report.aspect.LoggingAspect;
import com.capgemini.mbr.report.controller.ReportDownloadController;
import com.capgemini.mbr.report.exception.DataNotFoundException;
import com.capgemini.mbr.report.model.Report;
import com.capgemini.mbr.report.service.ReportService;
import com.capgemini.mbr.report.util.DateUtil;
import com.capgemini.mbr.report.util.PptGenerator;

@EnableAspectJAutoProxy
@RunWith(MockitoJUnitRunner.class)
public class ReportDownloadControlletTest {
	
	@InjectMocks
    ReportDownloadController reportController;
	@Mock
	MessageSource messageSource;
	@Mock
	private ReportService reportService;
	@Mock
	private DateUtil dateUtil;
	@Mock
	private ByteArrayInputStream byteArrayInputStream;
	@Mock
	private PptGenerator pptGenerator;
	@Before
	public void beforeTest() {
		 MockMvcBuilders.standaloneSetup(ReportDownloadController.class)
		  .setControllerAdvice(new LoggingAspect()) .build();
		 
	}

	@Test
	public void downloadReportTest() throws DataNotFoundException, IOException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		int  month = 07;
		int year = 2020;
		when(reportService.getReportsByMonthYear(07,2020)).thenReturn(getReportList());
		when(dateUtil.getMontYearPattern(07,2020,"MMM-yyyy")).thenReturn("Jul-2020");
		when(pptGenerator.generatePpt(any())).thenReturn(byteArrayInputStream);
		assertThat(reportController.downloadReport(month,year).getStatusCodeValue()).isEqualTo(200);
	}
	@Test(expected = DataNotFoundException.class)
	public void reportDataNotFoundDownloadReportTest() throws DataNotFoundException, IOException {
		int  month = 07;
		int year = 2020;
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		when(reportService.getReportsByMonthYear(month,year)).thenReturn(new ArrayList<>());
		reportController.downloadReport(month,year);
	}

	private List<Report> getReportList(){
		List<Report> reportlist = new ArrayList<>();
		reportlist.add(getReport());
     	return  reportlist;
	}
	private Report getReport(){
		Report report = new Report("report generation", "good", "no", "amit", LocalDate.now(), LocalDate.now());
      return report;
	}
}

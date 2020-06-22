package com.capgemini.mbr.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.capgemini.mbr.aspect.LoggingAspect;
import com.capgemini.mbr.exception.ReportNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.capgemini.mbr.bean.Response;
import com.capgemini.mbr.exception.ReportFoundException;
import com.capgemini.mbr.model.Report;
import com.capgemini.mbr.service.ReportService;

@EnableAspectJAutoProxy
@RunWith(MockitoJUnitRunner.class)
public class ReportControlletTest {
	
	@InjectMocks
    ReportController reportController;

	@Mock
	private ReportService reportService;
	@Before
	public void beforeTest() {
			MockMvcBuilders.standaloneSetup(ReportController.class)
				.setControllerAdvice(new LoggingAspect())
				.build();
	}
	@Test
	public void createReportTest() throws ReportFoundException {
		Report report = getReport();
		Report saveReport = new Report();
		saveReport.setReportId(123L);
		Optional<Report> optionalReport = Optional.empty();
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		when(reportService.findReportOfCurrentMonthByCreatedBy(report.getCreatedBy())).thenReturn(optionalReport);
		when(reportService.createReport(any(Report.class))).thenReturn(saveReport);
		ResponseEntity<Response> responseEntity = reportController.creatreReport(report);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
		
	}

	@Test(expected = ReportFoundException.class)
	public void createReportWhenReportCreatedExistsbyUserTest() throws ReportFoundException {
		Report reportToCreate = getReport();
		Optional<Report> optionalReport = Optional.of(reportToCreate);
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		when(reportService.findReportOfCurrentMonthByCreatedBy(reportToCreate.getCreatedBy())).thenReturn(optionalReport);
		ResponseEntity<Response> responseEntity = reportController.creatreReport(reportToCreate);
	}

	@Test
	public void getReportsByMonthYearTest(){
     List<Report> reportList = getReportList();
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		when(reportService.getReportsByMonthYear(06, 2020)).thenReturn(reportList);
		Assert.assertEquals(reportController.getReportsByMonthYear(06,2020).getStatusCode(), HttpStatus.OK);
		assertThat(reportController.getReportsByMonthYear(06,2020).getBody().size()).isEqualTo(1);
	}

	@Test
	public void updateReportTest() throws ReportFoundException, ReportNotFoundException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		Optional<Report> existingReport = Optional.of(getReport());
		when(reportService.findReportOfCurrentMonthByCreatedBy(getReport().getCreatedBy())).thenReturn(existingReport);
		Assert.assertEquals(reportController.updateReport(getReport()).getStatusCode(), HttpStatus.NO_CONTENT);
	}
	@Test(expected = ReportNotFoundException.class)
	public void updateReportReportNotFoundExceptionTest() throws ReportFoundException, ReportNotFoundException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		Optional<Report> existingReport = Optional.empty();
		when(reportService.findReportOfCurrentMonthByCreatedBy(getReport().getCreatedBy())).thenReturn(existingReport);
		Assert.assertEquals(reportController.updateReport(getReport()).getStatusCode(), HttpStatus.NO_CONTENT);
	}


	@Test
	public void getReportByUserIdTest() throws ReportNotFoundException, ReportFoundException{
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		Optional<Report> existingReport = Optional.of(getReport());

		when(reportService.findReportOfCurrentMonthByCreatedBy(getReport().getCreatedBy())).thenReturn(existingReport);
		assertThat(reportController.getReportByUserId(getReport().getCreatedBy()).getStatusCodeValue()).isEqualTo(200);
	}
	private List<Report> getReportList(){
		List<Report> reportlist = new ArrayList<>();
		reportlist.add(getReport());
     	return  reportlist;
	}

	private Report getReport(){
		Report report = new Report(123L, "Card control1", "card control desc1", "barclays1", "Bhavesh", "phase1",
				"successfully delivered", "report generation", "good", "no", "amit", LocalDate.now(), LocalDate.now());
      return report;
	}
}

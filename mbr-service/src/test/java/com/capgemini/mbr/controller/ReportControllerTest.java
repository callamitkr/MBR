package com.capgemini.mbr.controller;

import com.capgemini.mbr.aspect.LoggingAspect;
import com.capgemini.mbr.bean.Response;
import com.capgemini.mbr.exception.FoundException;
import com.capgemini.mbr.exception.NotFoundException;
import com.capgemini.mbr.model.Report;
import com.capgemini.mbr.service.ReportService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@EnableAspectJAutoProxy
@RunWith(MockitoJUnitRunner.class)
public class ReportControllerTest {
	
	@InjectMocks
    ReportController reportController;

	@Mock
	private ReportService reportService;
    @Mock
	MessageSource messageSource;
	@Before
	public void beforeTest() {
			MockMvcBuilders.standaloneSetup(ReportController.class)
				.setControllerAdvice(new LoggingAspect())
				.build();
	}
	@Test
	public void createReportTest() throws FoundException {
		Report report = getReport();
		Report saveReport = new Report();
		saveReport.setReportId(123L);
		Optional<Report> optionalReport = Optional.empty();
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		when(reportService.findReportOfCurrentMonthByuser(report.getCreatedBy())).thenReturn(optionalReport);
		when(reportService.createReport(any(Report.class))).thenReturn(saveReport);
		ResponseEntity<Response> responseEntity = reportController.creatReport(report);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
		
	}

	@Test(expected = FoundException.class)
	public void createReportWhenReportCreatedExistsbyUserTest() throws FoundException {
		Report reportToCreate = getReport();
		Optional<Report> optionalReport = Optional.of(reportToCreate);
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		when(reportService.findReportOfCurrentMonthByuser(reportToCreate.getCreatedBy())).thenReturn(optionalReport);
		ResponseEntity<Response> responseEntity = reportController.creatReport(reportToCreate);
	}

	@Test
	public void updateReportTest() throws FoundException, NotFoundException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		Optional<Report> existingReport = Optional.of(getReport());
		when(reportService.findReportOfCurrentMonthByuser(getReport().getCreatedBy())).thenReturn(existingReport);
		Assert.assertEquals(reportController.updateReport(getReport()).getStatusCode(), HttpStatus.ACCEPTED);
	}
	@Test(expected = NotFoundException.class)
	public void updateReportReportNotFoundExceptionTest() throws FoundException, NotFoundException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		Optional<Report> existingReport = Optional.empty();
		when(reportService.findReportOfCurrentMonthByuser(getReport().getCreatedBy())).thenReturn(existingReport);
		Assert.assertEquals(reportController.updateReport(getReport()).getStatusCode(), HttpStatus.ACCEPTED);
	}

	@Test
	public void getReportByUserIdTest() throws NotFoundException, FoundException{
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		Optional<Report> existingReport = Optional.of(getReport());

		when(reportService.findReportOfCurrentMonthByuser(getReport().getCreatedBy())).thenReturn(existingReport);
		assertThat(reportController.getReportByUserId(getReport().getCreatedBy()).getStatusCodeValue()).isEqualTo(200);
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

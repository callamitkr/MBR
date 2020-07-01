package com.capgemini.mbr.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.ARRAY;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.capgemini.mbr.aspect.LoggingAspect;
import com.capgemini.mbr.exception.ReportDataNotFoundException;
import com.capgemini.mbr.exception.ReportNotFoundException;
import com.capgemini.mbr.util.DateUtil;
import com.capgemini.mbr.util.PptGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.InputStreamResource;
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
	@Mock
	private DateUtil dateUtil;
	@Mock
	private ByteArrayInputStream byteArrayInputStream;
	@Mock
	private PptGenerator pptGenerator;
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
		when(reportService.findReportOfCurrentMonthByuser(report.getCreatedBy())).thenReturn(optionalReport);
		when(reportService.createReport(any(Report.class))).thenReturn(saveReport);
		ResponseEntity<Response> responseEntity = reportController.creatReport(report);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
		
	}

	@Test(expected = ReportFoundException.class)
	public void createReportWhenReportCreatedExistsbyUserTest() throws ReportFoundException {
		Report reportToCreate = getReport();
		Optional<Report> optionalReport = Optional.of(reportToCreate);
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		when(reportService.findReportOfCurrentMonthByuser(reportToCreate.getCreatedBy())).thenReturn(optionalReport);
		ResponseEntity<Response> responseEntity = reportController.creatReport(reportToCreate);
	}

	/*@Test
	public void getReportsByMonthYearTest(){
     List<Report> reportList = getReportList();
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		when(reportService.getReportsByMonthYear(06, 2020)).thenReturn(reportList);
		Assert.assertEquals(reportController.getReportsByMonthYear(06,2020).getStatusCode(), HttpStatus.OK);
		assertThat(reportController.getReportsByMonthYear(06,2020).getBody().size()).isEqualTo(1);
	}*/

	@Test
	public void updateReportTest() throws ReportFoundException, ReportNotFoundException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		Optional<Report> existingReport = Optional.of(getReport());
		when(reportService.findReportOfCurrentMonthByuser(getReport().getCreatedBy())).thenReturn(existingReport);
		Assert.assertEquals(reportController.updateReport(getReport()).getStatusCode(), HttpStatus.ACCEPTED);
	}
	@Test(expected = ReportNotFoundException.class)
	public void updateReportReportNotFoundExceptionTest() throws ReportFoundException, ReportNotFoundException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		Optional<Report> existingReport = Optional.empty();
		when(reportService.findReportOfCurrentMonthByuser(getReport().getCreatedBy())).thenReturn(existingReport);
		Assert.assertEquals(reportController.updateReport(getReport()).getStatusCode(), HttpStatus.ACCEPTED);
	}


	@Test
	public void getReportByUserIdTest() throws ReportNotFoundException, ReportFoundException{
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		Optional<Report> existingReport = Optional.of(getReport());

		when(reportService.findReportOfCurrentMonthByuser(getReport().getCreatedBy())).thenReturn(existingReport);
		assertThat(reportController.getReportByUserId(getReport().getCreatedBy()).getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	public void downloadReportTest() throws ReportDataNotFoundException, IOException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		String monthYear = "JUN-2020";
		byte[] byteArr = {'a','c','f'};
		ByteArrayInputStream out = new ByteArrayInputStream(byteArr);
		when(reportService.getReportsByCurrentMonthYear()).thenReturn(getReportList());
		when(dateUtil.getCurrentMontYear("MMM-yyyy")).thenReturn(monthYear);
		when(pptGenerator.ReportToPpt(any())).thenReturn(byteArrayInputStream);
		assertThat(reportController.downloadReport().getStatusCodeValue()).isEqualTo(200);
	}
	@Test(expected = ReportDataNotFoundException.class)
	public void reportDataNotFoundDownloadReportTest() throws ReportDataNotFoundException, IOException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		when(reportService.getReportsByCurrentMonthYear()).thenReturn(new ArrayList<>());
		reportController.downloadReport();
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

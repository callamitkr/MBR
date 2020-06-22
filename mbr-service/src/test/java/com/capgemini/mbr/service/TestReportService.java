package com.capgemini.mbr.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.mbr.model.Report;
import com.capgemini.mbr.repository.ReportRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TestReportService {

	@InjectMocks
	ReportSeviceImpl reportService;

	@Mock
	ReportRepository repository;

	@Test
	public void getReportsByMonthYearTest() {
		List<Report> reportList = new ArrayList<>();
		Report report1 = new Report(123L, "Card control1", "card control desc1", "barclays1", "Bhavesh", "phase1",
				"succesfully deliverd", "report generation", "good", "no", "amit", LocalDate.now(), LocalDate.now());
		Report report2 = new Report(124L, "Card control2", "card control desc2", "barclays2", "Bhavesh", "phase1",
				"succesfully deliverd", "report generation", "good", "no", "amit", LocalDate.now(), LocalDate.now());
		Report report3 = new Report(125L, "Card control3", "card control desc3", "barclays3", "Bhavesh", "phase1",
				"succesfully deliverd", "report generation", "good", "no", "amit", LocalDate.now(), LocalDate.now());
		reportList.add(report1);
		reportList.add(report2);
		reportList.add(report3);
		when(repository.getReportsByMonthYear(04, 2020)).thenReturn(reportList);
		List<Report> rlist = reportService.getReportsByMonthYear(04, 2020);
		assertEquals(3, rlist.size());
		verify(repository, times(1)).getReportsByMonthYear(04, 2020);

	}

	@Test
	public void findReportOfCurrentMonthByCreatedByTest() {
		Optional<Report> report = Optional.ofNullable(new Report(123L, "Card control", "card control desc1",
				"barclays1", "Bhavesh", "phase1", "succesfully deliverd", "report generation", "good", "no", "akuma397",
				LocalDate.now(), LocalDate.now()));

		when(repository.findReportOfCurrentMonthByCreatedBy("akum397")).thenReturn(report);
		Optional<Report> report1 = reportService.findReportOfCurrentMonthByCreatedBy("akum397");
		assertEquals(true, report1.isPresent());
		verify(repository, times(1)).findReportOfCurrentMonthByCreatedBy("akum397");
	}
	
	@Test
	public void createReportTest() {
		Report report = new Report(125L, "Card control", "card control desc3", "barclays3", "Bhavesh", "phase1",
				"succesfully deliverd", "report generation", "good", "no", "amit", LocalDate.now(), LocalDate.now());
		
		when(repository.save(report)).thenReturn(report);
		 Report createdReport = reportService.createReport(report);
		 assertEquals("Card control", createdReport.getProjectName());
		 verify(repository, times(1)).save(report);
	}
	
	@Test
	public void updateReport() {
		Report report = new Report(125L, "Card control", "card control desc3", "barclays3", "Bhavesh", "phase1",
				"succesfully deliverd", "report generation", "good", "no", "amit", LocalDate.now(), LocalDate.now());
		
		when(repository.save(report)).thenReturn(report);
		 Report createdReport = reportService.updateReport(report);
		 assertEquals("Card control", createdReport.getProjectName());
		 verify(repository, times(1)).save(report);
		
	}

}

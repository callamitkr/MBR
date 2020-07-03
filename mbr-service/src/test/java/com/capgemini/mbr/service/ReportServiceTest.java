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
public class ReportServiceTest {

	@InjectMocks
	ReportSeviceImpl reportService;

	@Mock
	ReportRepository repository;

	@Test
	public void getReportsByCurrentMonthYearTest() {
		List<Report> reportList = new ArrayList<>();
		int month = LocalDate.now().getMonthValue();
		int year = LocalDate.now().getYear();
		  Report report1 = new Report("report generation", "good", "no", "amit", LocalDate.now(), LocalDate.now());
		  Report report2 = new Report("report generation", "good", "no", "amit", LocalDate.now(), LocalDate.now());
		  Report report3 = new Report("report generation", "good", "no", "amit", LocalDate.now(), LocalDate.now());
		  reportList.add(report1); reportList.add(report2); reportList.add(report3);
		  when(repository.getReportsByCurrentMonthYear(month, year)).thenReturn(reportList);
		  List<Report> rlist = reportService.getReportsByCurrentMonthYear(); assertEquals(3, rlist.size());
		  verify(repository, times(1)).getReportsByCurrentMonthYear(month, year);

	}

	@Test
	public void findReportOfCurrentMonthByCreatedByTest() {

		  Optional<Report> report = Optional.ofNullable(new Report("report generation", "good", "no", "akuma397",
		 LocalDate.now(), LocalDate.now()));
		 when(repository.findReportOfCurrentMonthByuser("akum397")).thenReturn(report);
		 Optional<Report> report1 = reportService.findReportOfCurrentMonthByuser("akum397");
		 assertEquals(true,report1.isPresent());
		 verify(repository, times(1)).findReportOfCurrentMonthByuser("akum397");

	}
	
	@Test
	public void createReportTest() {
		Report report = new Report("Easy pay", "good", "no", "amit", LocalDate.now(), LocalDate.now());
		 when(repository.save(report)).thenReturn(report);
		 Report createdReport =  reportService.createReport(report);
		 assertEquals("good", createdReport.getBarclaysFeedback());
		 verify(repository, times(1)).save(report);

	}
	
	@Test
	public void updateReport() {
		Report report = new Report("Easy pay", "good", "no", "amit", LocalDate.now(), LocalDate.now());
		when(repository.save(report)).thenReturn(report); Report createdReport =
		reportService.updateReport(report);
		assertEquals("Easy pay",createdReport.getKeyHighlights()); verify(repository, times(1)).save(report);
	}

}

package com.capgemini.mbr.report.service;

import com.capgemini.mbr.report.model.Report;
import com.capgemini.mbr.report.repository.ReportRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ReportServiceTest {
  @InjectMocks
  ReportSeviceImpl reportSevice;
  @Mock
  ReportRepository reportRepository;

  @Test
    public void getReportsByCurrentMonthYearTest(){
      int month = LocalDate.now().getMonthValue();
      int year = LocalDate.now().getYear();
      when(reportRepository.getReportsBYMonthYear(month,year)).thenReturn(getReportList());
      List<Report> list = reportSevice.getReportsByMonthYear(month,year);
      assertEquals(true,list.size()>0);

  }
  private List<Report> getReportList(){
      List<Report> reportList = new ArrayList<>();
      Report report = new Report("Easy pay", "good", "no", "amit", LocalDate.now(), LocalDate.now());
      reportList.add(report);
      return  reportList;
  }

}

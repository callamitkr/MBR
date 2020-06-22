package com.capgemini.mbr.service;

import java.util.List;
import java.util.Optional;

import com.capgemini.mbr.exception.ReportFoundException;
import com.capgemini.mbr.model.Report;

public interface ReportService {
	
	List<Report>getReportsByMonthYear(int month,int year);
	List<Report>getReportsByCurrentMonthYear();
	Report createReport(Report report);
	Optional<Report> findReportOfCurrentMonthByCreatedBy(String userId) throws ReportFoundException;
	Report updateReport(Report report);
	
   
}

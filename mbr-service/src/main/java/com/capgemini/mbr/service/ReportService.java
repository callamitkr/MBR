package com.capgemini.mbr.service;

import java.util.List;
import java.util.Optional;

import com.capgemini.mbr.exception.ReportFoundException;
import com.capgemini.mbr.model.Report;

public interface ReportService {

	Optional<Report> findReportOfCurrentMonthByuser(String userId) throws ReportFoundException;
	Report createReport(Report report);
	Report updateReport(Report report);
	List<Report>getReportsByCurrentMonthYear();
   
}

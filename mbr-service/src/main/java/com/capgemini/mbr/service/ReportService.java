package com.capgemini.mbr.service;

import com.capgemini.mbr.exception.FoundException;
import com.capgemini.mbr.model.Report;

import java.util.List;
import java.util.Optional;

public interface ReportService {

	Optional<Report> findReportOfCurrentMonthByuser(String userId) throws FoundException;
	Report createReport(Report report);
	Report updateReport(Report report);
	List<Report>getReportsByCurrentMonthYear();
   
}

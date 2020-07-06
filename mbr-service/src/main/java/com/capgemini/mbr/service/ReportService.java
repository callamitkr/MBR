package com.capgemini.mbr.service;

import com.capgemini.mbr.exception.DataFoundException;
import com.capgemini.mbr.model.Report;

import java.util.Optional;

public interface ReportService {

	Optional<Report> findReportOfCurrentMonthByuser(String userId) throws DataFoundException;
	Report createReport(Report report);
	Report updateReport(Report report);

   
}

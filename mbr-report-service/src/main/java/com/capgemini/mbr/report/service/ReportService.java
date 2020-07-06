package com.capgemini.mbr.report.service;

import java.util.List;

import com.capgemini.mbr.report.model.Report;



public interface ReportService {
	List<Report>getReportsByCurrentMonthYear();
}

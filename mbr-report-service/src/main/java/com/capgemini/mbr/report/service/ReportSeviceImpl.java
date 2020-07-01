package com.capgemini.mbr.report.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.mbr.report.model.Report;
import com.capgemini.mbr.report.repository.ReportRepository;



@Service
public class ReportSeviceImpl implements ReportService {

	private static final Logger logger = LoggerFactory.getLogger(ReportSeviceImpl.class);

	@Autowired
	ReportRepository repository;

	@Override
	public Report createReport(Report report) {
		report.setCreatedDate(LocalDate.now());
		report.setUpdatedDate(LocalDate.now());
		return repository.save(report);
	}

	@Override
	public Optional<Report> findReportOfCurrentMonthByuser(String userId) {
		logger.info("Searching the report creted by {}", userId);
		return repository.findReportOfCurrentMonthByuser(userId);
	}

	@Override
	public Report updateReport(Report report) {
		report.setUpdatedDate(LocalDate.now());
		return repository.save(report);
	}


	@Override
	public List<Report> getReportsByCurrentMonthYear() {
		int month = LocalDate.now().getMonthValue();
		int year = LocalDate.now().getYear();
		return repository.getReportsByCurrentMonthYear(month, year);
	}

}

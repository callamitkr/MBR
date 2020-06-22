package com.capgemini.mbr.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.mbr.model.Report;
import com.capgemini.mbr.repository.ReportRepository;

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
	public Optional<Report> findReportOfCurrentMonthByCreatedBy(String userId) {
		logger.info("Searching the report creted by {}", userId);
		return repository.findReportOfCurrentMonthByCreatedBy(userId);
	}

	@Override
	public Report updateReport(Report report) {
		report.setUpdatedDate(LocalDate.now());
		return repository.save(report);
	}

	@Override
	public List<Report> getReportsByMonthYear(int month, int year) {
		return repository.getReportsByMonthYear(month, year);
	}

	@Override
	public List<Report> getReportsByCurrentMonthYear() {
		int month = LocalDate.now().getMonthValue();
		int year = LocalDate.now().getYear();
		return repository.getReportsByMonthYear(month, year);
	}

}

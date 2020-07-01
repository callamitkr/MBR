package com.capgemini.mbr.controller;


import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.mbr.bean.Response;
import com.capgemini.mbr.exception.ReportFoundException;
import com.capgemini.mbr.exception.ReportNotFoundException;
import com.capgemini.mbr.model.Report;
import com.capgemini.mbr.service.ReportService;

@RestController
public class ReportController {
	private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

	@Autowired
	private ReportService reportService;
	
	@PostMapping("/reports")
	public ResponseEntity<Response> creatReport(@Valid @RequestBody Report newReport) throws ReportFoundException {
	//	logger.info("Inside createReport Controller");
		Optional<Report> existingReport = reportService.findReportOfCurrentMonthByuser(newReport.getCreatedBy());
		if (existingReport.isPresent()) {
			throw new ReportFoundException("You have already created report for Current month");
		}
		//logger.info("Report found : {}", existingReport);
		Report savedReport = reportService.createReport(newReport);
		//logger.info("Report saved with reportId : {}",savedReport.getReportId());
		return new ResponseEntity<>(new Response("Report Created", HttpStatus.CREATED),HttpStatus.CREATED);
	}

	@PutMapping("/updateReport")
	public ResponseEntity<Response> updateReport(@Valid @RequestBody Report report)
			throws ReportNotFoundException, ReportFoundException {
	//	logger.info("Inside updateReport Controller");
		Optional<Report> existingReport = reportService.findReportOfCurrentMonthByuser(report.getCreatedBy());
		if (existingReport.isPresent()) {
			report.setReportId(existingReport.get().getReportId());
			report.setCreatedDate(existingReport.get().getCreatedDate());
		} else {
			throw new ReportNotFoundException("You have not created report for current month");
		}
		reportService.updateReport(report);
		return new ResponseEntity<>(new Response("Report Updated",HttpStatus.ACCEPTED),HttpStatus.ACCEPTED);

	}

	@GetMapping("/getReport/{userId}")
	public ResponseEntity<Report> getReportByUserId(@PathVariable(value = "userId") String userId)
			throws ReportNotFoundException, ReportFoundException {
		//logger.info("Inside getReportByUserId Controller");
		Report report = reportService.findReportOfCurrentMonthByuser(userId)
				.orElseThrow(() -> new ReportNotFoundException("You have not created report for current month"));
		return ResponseEntity.ok().body(report);
	}
}

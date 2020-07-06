package com.capgemini.mbr.controller;


import com.capgemini.mbr.bean.Response;
import com.capgemini.mbr.constant.MbrConstant;
import com.capgemini.mbr.exception.DataFoundException;
import com.capgemini.mbr.exception.DataNotFoundException;
import com.capgemini.mbr.model.Report;
import com.capgemini.mbr.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;
import java.util.Optional;

@RestController
public class ReportController {
	@Autowired
	private ReportService reportService;
	@Autowired
	private MessageSource messageSource;

	@PostMapping("/createReport")
	public ResponseEntity<Response> creatReport(@Valid @RequestBody Report newReport) throws DataFoundException {
		Optional<Report> existingReport = reportService.findReportOfCurrentMonthByuser(newReport.getCreatedBy());
		if (existingReport.isPresent()) {
			throw new DataFoundException(messageSource.getMessage("report.create.error",null,Locale.getDefault()));
		}
		Report savedReport = reportService.createReport(newReport);
		return new ResponseEntity<>(new Response(messageSource.getMessage("report.created",null,Locale.getDefault()), HttpStatus.CREATED),HttpStatus.CREATED);
	}

	@PutMapping("/updateReport")
	public ResponseEntity<Response> updateReport(@Valid @RequestBody Report report)
			throws DataNotFoundException, DataFoundException {
		Optional<Report> existingReport = reportService.findReportOfCurrentMonthByuser(report.getCreatedBy());
		if (existingReport.isPresent()) {
			report.setReportId(existingReport.get().getReportId());
			report.setCreatedDate(existingReport.get().getCreatedDate());
		} else {
			throw new DataNotFoundException(messageSource.getMessage("report.fetch.error",null,Locale.getDefault()));
		}
		reportService.updateReport(report);
		return new ResponseEntity<>(new Response(messageSource.getMessage("report.updated",null, Locale.getDefault()),HttpStatus.ACCEPTED),HttpStatus.ACCEPTED);

	}

	@GetMapping("/getReport/{userId}")
	public ResponseEntity<Report> getReportByUserId(@PathVariable(value = "userId") String userId)
			throws DataNotFoundException, DataFoundException {
		Report report = reportService.findReportOfCurrentMonthByuser(userId)
				.orElseThrow(() -> new DataNotFoundException(messageSource.getMessage("report.fetch.error",null,Locale.getDefault())));
		return ResponseEntity.ok().body(report);
	}
}

package com.capgemini.mbr.report.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.mbr.report.exception.ReportDataNotFoundException;
import com.capgemini.mbr.report.model.Report;
import com.capgemini.mbr.report.service.ReportService;
import com.capgemini.mbr.report.util.DateUtil;
import com.capgemini.mbr.report.util.PptGenerator;



@RestController
public class ReportDownloadController {
	
	@Autowired
	private ReportService reportService;
	@Autowired
	private PptGenerator pptGenerator;
	@Autowired
	private DateUtil dateUtil;
	private String  monthYear;
	private  List<Report> listOfReport;
	private ByteArrayInputStream byteArrayInputStream;
	private HttpHeaders headers;
	@GetMapping(value = "/download")
	public ResponseEntity<InputStreamResource> downloadReport() throws ReportDataNotFoundException,IOException  {
		listOfReport    = reportService.getReportsByCurrentMonthYear();
	     if(listOfReport.size() == 0){
	 	   throw new ReportDataNotFoundException("No report available for current month ");
	     }
		monthYear = dateUtil.getCurrentMontYear("MMM-yyyy");
		byteArrayInputStream  = pptGenerator.ReportToPpt(listOfReport);
		headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=MBR-"+monthYear+".pptx");
		byteArrayInputStream.close();
		return ResponseEntity
				.ok()
				.headers(headers)
				.body(new InputStreamResource(byteArrayInputStream));
	}

}

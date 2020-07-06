package com.capgemini.mbr.report.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.mbr.report.exception.DataNotFoundException;
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
	@Autowired
    MessageSource messageSource ;
	private String  monthYear;
	private String reportName;
	private List<Report> listOfReport;
	private ByteArrayInputStream byteArrayInputStream;
	private HttpHeaders headers;
	
	@GetMapping(value = "/download")
	public ResponseEntity<InputStreamResource> downloadReport() throws DataNotFoundException,IOException  {
		listOfReport    = reportService.getReportsByCurrentMonthYear();
	     if(listOfReport.size() == 0){
	 	   throw new DataNotFoundException(messageSource.getMessage("report.notfound.error",null, Locale.getDefault()));
	     }
		
		byteArrayInputStream  = pptGenerator.generatePpt(listOfReport);
		monthYear = dateUtil.getCurrentMontYear("MMM-yyyy");
		reportName = messageSource.getMessage("report.name",null, Locale.getDefault());
		headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename="+String.join(".",String.join("-",reportName,monthYear),"pptx"));
		byteArrayInputStream.close();
		return ResponseEntity
				.ok()
				.headers(headers)
				.body(new InputStreamResource(byteArrayInputStream));
	}

}

package com.capgemini.mbr.report.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.capgemini.mbr.report.constant.ReportConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleDataNotFoundException(DataNotFoundException execption) {
		List<String> description = new ArrayList<>();
		description.add(execption.getMessage());
		ErrorDetails errorDetails = new ErrorDetails(new Date().getTime(), ReportConstant.DATA_NOT_FOUND_EX, description);
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<ErrorDetails> handleAllException(Exception execption) {
		List<String> description = new ArrayList<>();
		description.add(execption.getMessage());
		ErrorDetails errorDetails = new ErrorDetails(new Date().getTime(), execption.getClass().getSimpleName(), description);
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

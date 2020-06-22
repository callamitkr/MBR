package com.capgemini.mbr.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(ReportDataNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ReportDataNotFoundException execption) {
		List<String> description = new ArrayList<>();
		description.add(execption.getMessage());
		logger.error("Error has occurred {} ",description);
		ErrorDetails errorDetails = new ErrorDetails(new Date().getTime(), "Report data not Found", description);
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(ReportNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ReportNotFoundException execption) {
		List<String> description = new ArrayList<>();
		description.add(execption.getMessage());
		logger.error("Error has occurred {} ",description);
        ErrorDetails errorDetails = new ErrorDetails(new Date().getTime(), "Report Not Found", description);
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
   }

	@ExceptionHandler(ReportFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceFoundException(ReportFoundException execption) {
		List<String> description = new ArrayList<>();
		description.add(execption.getMessage());
		logger.error("Error has occurred {} ",description);
		ErrorDetails errorDetails = new ErrorDetails(new Date().getTime(), "Report Exists", description);
		return new ResponseEntity<>(errorDetails, HttpStatus.FOUND);
	}
   @ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException execption) {
		List<String> description = new ArrayList<>();
		for(ObjectError error : execption.getBindingResult().getAllErrors()) {
			description.add(error.getDefaultMessage());
		}
	   logger.error("Validation has occurred {} ",description);
			ErrorDetails error = new ErrorDetails(new Date().getTime(),"Validation Failed", description);
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleAllException(Exception execption) {

		List<String> description = new ArrayList<>();
		description.add(execption.getMessage());
		logger.error("Error has occurred {}",description);

		ErrorDetails errorDetails = new ErrorDetails(new Date().getTime(),"Server Error", description);
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

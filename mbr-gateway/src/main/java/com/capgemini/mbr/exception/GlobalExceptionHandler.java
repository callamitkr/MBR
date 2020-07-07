package com.capgemini.mbr.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.netflix.zuul.exception.ZuulException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleAllException(Exception ex) {
		List<String> description = new ArrayList<>();
		description.add(ex.getMessage());
		ErrorDetails errorDetails = new ErrorDetails(new Date().getTime(), ex.getCause().toString(), description);
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(ZuulException.class)
	public ResponseEntity<ErrorDetails> handleZuulException(Exception ex) {
		List<String> description = new ArrayList<>();
		description.add(ex.getMessage());
		ErrorDetails errorDetails = new ErrorDetails(new Date().getTime(), ex.getCause().toString(), description);
		return new ResponseEntity<>(errorDetails, HttpStatus.GATEWAY_TIMEOUT);
	}

}

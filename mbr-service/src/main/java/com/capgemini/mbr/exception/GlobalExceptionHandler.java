package com.capgemini.mbr.exception;

import com.capgemini.mbr.constant.MbrConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DataFoundException.class)
	public ResponseEntity<ErrorDetails> handleFoundException(DataFoundException ex) {
		List<String> description = new ArrayList<>();
		description.add(ex.getMessage());
		ErrorDetails errorDetails = new ErrorDetails(new Date().getTime(), MbrConstant.DATA_FOUND_EX, description);
		return new ResponseEntity<>(errorDetails, HttpStatus.FOUND);
	}

	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleNotFoundException(DataNotFoundException ex) {
		List<String> description = new ArrayList<>();
		description.add(ex.getMessage());
		ErrorDetails errorDetails = new ErrorDetails(new Date().getTime(),MbrConstant.DATA_NOT_FOUND_EX, description);
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

   @ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException execption) {
		List<String> description = new ArrayList<>();
		for(ObjectError  error : execption.getBindingResult().getAllErrors()) {
			description.add(error.getDefaultMessage());
		}
			ErrorDetails error = new ErrorDetails(new Date().getTime(),MbrConstant.METHOD_ARG_NOT_VALID,description);
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataAccessException.class)
	public ResponseEntity<ErrorDetails> handleDataAccessException(DataAccessException ex) {
		List<String> description = new ArrayList<>();
		description.add(ex.getMessage());
		ErrorDetails errorDetails = new ErrorDetails(new Date().getTime(),MbrConstant.DATA_ACCESS_EX, description);
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleAllException(Exception execption) {
		List<String> description = new ArrayList<>();
		description.add(execption.getMessage());
		ErrorDetails errorDetails = new ErrorDetails(new Date().getTime(),MbrConstant.SERVER_ERROR, description);
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

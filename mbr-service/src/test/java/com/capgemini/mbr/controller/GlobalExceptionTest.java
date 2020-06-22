package com.capgemini.mbr.controller;

import com.capgemini.mbr.exception.GlobalExceptionHandler;
import com.capgemini.mbr.exception.ReportFoundException;
import com.capgemini.mbr.exception.ReportNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.validation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class GlobalExceptionTest {
    @InjectMocks
    GlobalExceptionHandler globalExceptionHandler;
    @Test
    public void reportNotFoundExceptionTest() throws Exception {
        ReportNotFoundException rp = new ReportNotFoundException("Report not found");
        GlobalExceptionHandler gg = new GlobalExceptionHandler();
        assertThat(gg.handleResourceNotFoundException(rp).getStatusCodeValue()).isEqualTo(404);
      }
    @Test
    public void reportFoundExceptionTest() throws Exception {
        ReportFoundException reportFoundException = new ReportFoundException("Report found");
         assertThat(globalExceptionHandler.handleResourceFoundException(reportFoundException).getStatusCodeValue()).isEqualTo(302);
    }
    @Test
    public void methodArgumentNotValidTest() throws Exception {

       /* BeanPropertyBindingResult errors = new BeanPropertyBindingResult(new Report(), "testBean");
        errors.rejectValue("bu", "invalid");
        MethodParameter parameter = new MethodParameter(this.getClass().getMethod("handle", String.class), 0);
        MethodArgumentNotValidException methodArgumentNotValidException = new MethodArgumentNotValidException(parameter, errors);
       */ BindingResult bindingResult = new MapBindingResult(Collections.singletonMap("a", "b"), "objectName");
        bindingResult.addError(new ObjectError("c", "d"));
        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindingResult);
        //testBindingResult(bindingResult, ex);

      assertThat(globalExceptionHandler.handleMethodArgumentNotValid(ex).getStatusCodeValue()).isEqualTo(400);
    }

    @Test
    public void handleAllExceptionTest() throws Exception {
        Exception execption = new Exception("All Error");
        assertThat(globalExceptionHandler.handleAllException(execption).getStatusCodeValue()).isEqualTo(500);
    }
}

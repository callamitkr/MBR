package com.capgemini.mbr.report.test.controller;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.capgemini.mbr.report.exception.GlobalExceptionHandler;
import com.capgemini.mbr.report.exception.ReportDataNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class GlobalExceptionTest {
    @InjectMocks
    GlobalExceptionHandler globalExceptionHandler;

    @Test
    public void ReportDataNotFoundExceptionTest(){
        ReportDataNotFoundException reportDataNotFoundException = new ReportDataNotFoundException("Report not found");
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        assertThat(globalExceptionHandler.handleResourceNotFoundException(reportDataNotFoundException).getStatusCodeValue()).isEqualTo(404);

    }

    @Test
    public void handleAllExceptionTest() throws Exception {
        Exception execption = new Exception("All Error");
        assertThat(globalExceptionHandler.handleAllException(execption).getStatusCodeValue()).isEqualTo(500);
    }


}

package com.capgemini.mbr.report.execption;


import static org.assertj.core.api.Assertions.assertThat;

import com.capgemini.mbr.report.constant.ReportConstant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.capgemini.mbr.report.exception.GlobalExceptionHandler;
import com.capgemini.mbr.report.exception.DataNotFoundException;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class GlobalExceptionTest {
    @InjectMocks
    GlobalExceptionHandler globalExceptionHandler;

    @Test
    public void ReportDataNotFoundExceptionTest(){
        DataNotFoundException dataNotFoundException = new DataNotFoundException("Report not found");
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        assertThat(globalExceptionHandler.handleDataNotFoundException(dataNotFoundException).getStatusCodeValue()).isEqualTo(404);
        assertThat(globalExceptionHandler.handleDataNotFoundException(dataNotFoundException).getBody().getDescription().get(0)).isEqualTo(dataNotFoundException.getMessage());
        assertThat(globalExceptionHandler.handleDataNotFoundException(dataNotFoundException).getBody().getError()).isEqualTo(ReportConstant.DATA_NOT_FOUND_EX);
    }

    @Test
    public void handleAllExceptionTest() throws Exception {
        Exception execption = new Exception("All Error");
        Long timeStamp = new Date().getTime();
        assertThat(globalExceptionHandler.handleAllException(execption).getBody().getTimestamp()).isEqualTo(timeStamp);
        assertThat(globalExceptionHandler.handleAllException(execption).getStatusCodeValue()).isEqualTo(500);
    }


}

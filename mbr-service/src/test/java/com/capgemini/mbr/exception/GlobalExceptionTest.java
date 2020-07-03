package com.capgemini.mbr.exception;

import com.capgemini.mbr.constant.MbrConstant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GlobalExceptionTest {
    @InjectMocks
    GlobalExceptionHandler globalExceptionHandler;
    @Mock
    MessageSource messageSource;
    @Mock
    DataAccessException dataAccessException;
   /* @Test
    public void ReportDataNotFoundExceptionTest(){
        ReportDataNotFoundException reportDataNotFoundException = new ReportDataNotFoundException("Report not found");
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        assertThat(globalExceptionHandler.handleResourceNotFoundException(reportDataNotFoundException).getStatusCodeValue()).isEqualTo(404);
    }
*/
    @Test
    public void notFoundExceptionTest() throws Exception {
        NotFoundException notFoundException = new NotFoundException(MbrConstant.REPORT_MESSAGE_KEY,"Report not found");
        //when(messageSource.getMessage("report.found",null, Locale.getDefault())).thenReturn("Report not found");
        assertThat(globalExceptionHandler.handleNotFoundException(notFoundException).getStatusCodeValue()).isEqualTo(404);
        assertThat(globalExceptionHandler.handleNotFoundException(notFoundException).getBody().getDescription().get(0)).isEqualTo(notFoundException.getMessage());
    }
    @Test
    public void foundExceptionTest() throws Exception {
        FoundException foundException = new FoundException(MbrConstant.REPORT_MESSAGE_KEY,"Report found");
        assertThat(globalExceptionHandler.handleFoundException(foundException).getStatusCodeValue()).isEqualTo(302);
        assertThat(globalExceptionHandler.handleFoundException(foundException).getBody().getError()).isEqualTo(null);
    }
    @Test
    public void handleDataAccessExceptionTest() throws Exception {
       // DataAccessException dataAccessException = new DataAccessException("id","Id not found");
        assertThat(globalExceptionHandler.handleDataAccessException(dataAccessException).getStatusCodeValue()).isEqualTo(400);
        assertThat(globalExceptionHandler.handleDataAccessException(dataAccessException).getBody().getError()).isEqualTo("DataAccessException");
    }

    @Test
    public void methodArgumentNotValidTest() throws Exception {
        BindingResult bindingResult = new MapBindingResult(Collections.singletonMap("a", "b"), "objectName");
        bindingResult.addError(new ObjectError("c", "d"));
        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindingResult);
        assertThat(globalExceptionHandler.handleMethodArgumentNotValid(ex).getStatusCodeValue()).isEqualTo(400);
    }

    @Test
    public void handleAllExceptionTest() throws Exception {
        Exception execption = new Exception("All Error");
        assertThat(globalExceptionHandler.handleAllException(execption).getStatusCodeValue()).isEqualTo(500);
        Long timeStamp = new Date().getTime();
        assertThat(globalExceptionHandler.handleAllException(execption).getBody().getTimestamp()).isEqualTo(timeStamp);
    }
}

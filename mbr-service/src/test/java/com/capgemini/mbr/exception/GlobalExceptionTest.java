package com.capgemini.mbr.exception;

import com.capgemini.mbr.constant.MbrConstant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Collections;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class GlobalExceptionTest {
    @InjectMocks
    GlobalExceptionHandler globalExceptionHandler;
    @Mock
    MessageSource messageSource;
    @Mock
    DataAccessException dataAccessException;

    @Test
    public void notFoundExceptionTest() throws Exception {
        DataNotFoundException dataNotFoundException = new DataNotFoundException("Report not found");
        assertThat(globalExceptionHandler.handleNotFoundException(dataNotFoundException).getStatusCodeValue()).isEqualTo(404);
        assertThat(globalExceptionHandler.handleNotFoundException(dataNotFoundException).getBody().getDescription().get(0)).isEqualTo(dataNotFoundException.getMessage());
    }

    @Test
    public void foundExceptionTest() throws Exception {
        DataFoundException dataFoundException = new DataFoundException("Report found");
        assertThat(globalExceptionHandler.handleFoundException(dataFoundException).getStatusCodeValue()).isEqualTo(302);
        assertThat(globalExceptionHandler.handleFoundException(dataFoundException).getBody().getError()).isEqualTo(MbrConstant.DATA_FOUND_EX);
    }

    @Test
    public void handleDataAccessExceptionTest() throws Exception {
        assertThat(globalExceptionHandler.handleDataAccessException(dataAccessException).getStatusCodeValue()).isEqualTo(400);
        assertThat(globalExceptionHandler.handleDataAccessException(dataAccessException).getBody().getError()).isEqualTo(MbrConstant.DATA_ACCESS_EX);
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

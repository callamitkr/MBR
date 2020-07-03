package com.capgemini.mbr.controller;

import com.capgemini.mbr.aspect.LoggingAspect;
import com.capgemini.mbr.bean.Response;
import com.capgemini.mbr.exception.FoundException;
import com.capgemini.mbr.exception.NotFoundException;
import com.capgemini.mbr.model.Bu;
import com.capgemini.mbr.model.Report;
import com.capgemini.mbr.service.BuService;
import com.capgemini.mbr.service.ReportService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@EnableAspectJAutoProxy
@RunWith(MockitoJUnitRunner.class)
public class BuControllerTest {
    @InjectMocks
    BuController buController;
    @Mock
    private BuService buService;
    @Mock
    MessageSource messageSource;
    @Before
    public void beforeTest() {
        MockMvcBuilders.standaloneSetup(BuController.class)
                .setControllerAdvice(new LoggingAspect())
                .build();
    }
    @Test
    public void createBuTest() throws FoundException {
        Optional<Bu> optionalBu = Optional.empty();
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(buService.createBu(any(Bu.class))).thenReturn(getBu());
        ResponseEntity<Response> responseEntity = buController.createBu(getBu());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
    }

    @Test(expected = FoundException.class)
    public void createBuWhenReportBuExistsTest() throws FoundException {
        Bu bu = new Bu();
        bu.setBuId(1L);
        bu.setBu("US");
        Optional<Bu> optionalBu = Optional.of(bu);
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(buService.findBu(1L)).thenReturn(optionalBu);
        ResponseEntity<Response> responseEntity = buController.createBu(bu);
    }
    @Test
    public void updateBuTest() throws NotFoundException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Optional<Bu> existingBu =  Optional.of(getBu());
        when(buService.findBu(getBu().getBuId())).thenReturn(existingBu);
        Assert.assertEquals(buController.updateBu(new Bu()).getStatusCode(), HttpStatus.ACCEPTED);
    }

    @Test(expected = NotFoundException.class)
    public void updateBuNotFoundExceptionTest() throws FoundException, NotFoundException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Optional<Bu> existingBu = Optional.empty();
//        when(buService.findBu(1L)).thenReturn(existingBu);
        Assert.assertEquals(buController.updateBu(getBu()).getStatusCode(), HttpStatus.ACCEPTED);
    }
    @Test
    public void delteBuTest() throws NotFoundException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Optional<Bu> existingBu =  Optional.of(getBu());
        Assert.assertEquals(buController.deleteBu(1L).getStatusCode(), HttpStatus.ACCEPTED);
    }


    private Bu getBu(){
        Bu bu = new Bu("US");
        return bu;
    }
}

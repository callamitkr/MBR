package com.capgemini.mbr.controller;

import com.capgemini.mbr.aspect.LoggingAspect;
import com.capgemini.mbr.bean.Response;
import com.capgemini.mbr.exception.DataFoundException;
import com.capgemini.mbr.exception.DataNotFoundException;
import com.capgemini.mbr.model.Bu;
import com.capgemini.mbr.service.BuService;
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

import java.util.ArrayList;
import java.util.List;
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
    public void createBuTest() throws DataFoundException {
        Optional<Bu> optionalBu = Optional.empty();
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(buService.createBu(any(Bu.class))).thenReturn(getBu());
        ResponseEntity<Response> responseEntity = buController.createBu(getBu());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
    }

    @Test(expected = DataFoundException.class)
    public void createBuWhenReportBuExistsTest() throws DataFoundException {
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
    public void updateBuTest() throws DataNotFoundException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Optional<Bu> existingBu =  Optional.of(getBu());
        when(buService.findBu(getBu().getBuId())).thenReturn(existingBu);
        Assert.assertEquals(buController.updateBu(new Bu()).getStatusCode(), HttpStatus.ACCEPTED);
    }

    @Test(expected = DataNotFoundException.class)
    public void updateBuNotFoundExceptionTest() throws DataFoundException, DataNotFoundException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Optional<Bu> existingBu = Optional.empty();
        Assert.assertEquals(buController.updateBu(getBu()).getStatusCode(), HttpStatus.ACCEPTED);
    }
    @Test
    public void deleteBuTest() throws DataNotFoundException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Optional<Bu> existingBu =  Optional.of(getBu());
        Assert.assertEquals(buController.deleteBu(1L).getStatusCode(), HttpStatus.ACCEPTED);
    }

    @Test
    public void getBuTest() throws DataNotFoundException {
        List<Bu> buList = new ArrayList<>();
        buList.add(getBu());
        when(buService.getBu()).thenReturn(buList);
        Assert.assertEquals(buController.getBu().getStatusCode(),HttpStatus.OK);
    }
    @Test(expected = DataNotFoundException.class)
    public void getBuDataNotFoundTest() throws DataNotFoundException {
        buController.getBu();
    }

    private Bu getBu(){
        Bu bu = new Bu("US");
        return bu;
    }
}

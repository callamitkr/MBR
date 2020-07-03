package com.capgemini.mbr.controller;

import com.capgemini.mbr.aspect.LoggingAspect;
import com.capgemini.mbr.bean.Response;
import com.capgemini.mbr.exception.FoundException;
import com.capgemini.mbr.exception.NotFoundException;
import com.capgemini.mbr.model.Phase;
import com.capgemini.mbr.service.PhaseService;
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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@EnableAspectJAutoProxy
@RunWith(MockitoJUnitRunner.class)
public class PhaseControllerTest {
    @InjectMocks
    PhaseController phaseController;
    @Mock
    private PhaseService phaseService;
    @Mock
    MessageSource messageSource;
    @Before
    public void beforeTest() {
        MockMvcBuilders.standaloneSetup(PhaseController.class)
                .setControllerAdvice(new LoggingAspect())
                .build();
    }
    @Test
    public void createPhaseTest() throws FoundException {
        Optional<Phase> optionalPhase = Optional.empty();
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(phaseService.createPhase(any(Phase.class))).thenReturn(getPhase());
        ResponseEntity<Response> responseEntity = phaseController.createPhase(getPhase());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
    }

    @Test(expected = FoundException.class)
    public void createPhaseWhenReportPhaseExistsTest() throws FoundException {
        Phase phase = new Phase();
        phase.setPhaseId(1L);
        phase.setPhase("US");
        Optional<Phase> optionalPhase = Optional.of(phase);
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(phaseService.findPhase(1L)).thenReturn(optionalPhase);
        ResponseEntity<Response> responseEntity = phaseController.createPhase(phase);
    }
    @Test
    public void updatePhaseTest() throws NotFoundException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Optional<Phase> existingPhase =  Optional.of(getPhase());
        when(phaseService.findPhase(getPhase().getPhaseId())).thenReturn(existingPhase);
        Assert.assertEquals(phaseController.updatePhase(new Phase()).getStatusCode(), HttpStatus.ACCEPTED);
    }

    @Test(expected = NotFoundException.class)
    public void updatePhaseNotFoundExceptionTest() throws FoundException, NotFoundException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Optional<Phase> existingPhase = Optional.empty();
        Assert.assertEquals(phaseController.updatePhase(getPhase()).getStatusCode(), HttpStatus.ACCEPTED);
    }
    @Test
    public void deltePhaseTest() throws NotFoundException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Optional<Phase> existingPhase =  Optional.of(getPhase());
        Assert.assertEquals(phaseController.deletePhase(1L).getStatusCode(), HttpStatus.ACCEPTED);
    }


    private Phase getPhase(){
        Phase phase = new Phase("dev");
        return phase;
    }
}

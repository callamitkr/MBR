package com.capgemini.mbr.controller;

import com.capgemini.mbr.aspect.LoggingAspect;
import com.capgemini.mbr.bean.Response;
import com.capgemini.mbr.exception.FoundException;
import com.capgemini.mbr.exception.NotFoundException;
import com.capgemini.mbr.model.Bu;
import com.capgemini.mbr.model.ProjectStatus;
import com.capgemini.mbr.service.ProjectStatusService;
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
public class ProjectStatusControllerTest {
    @InjectMocks
    ProjectStatusController projectStatusController;
    @Mock
    private ProjectStatusService projectStatusService;
    @Mock
    MessageSource messageSource;
    @Before
    public void beforeTest() {
        MockMvcBuilders.standaloneSetup(ProjectStatusController.class)
                .setControllerAdvice(new LoggingAspect())
                .build();
    }
    @Test
    public void createProjectStatusTest() throws FoundException {
        Optional<ProjectStatus> optionalProjectStatus = Optional.empty();
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(projectStatusService.createProjectStatus(any(ProjectStatus.class))).thenReturn(getProjectStatus());
        ResponseEntity<Response> responseEntity = projectStatusController.createProjectStatus(getProjectStatus());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
    }

    @Test(expected = FoundException.class)
    public void createProjectStatusWhenProjectStatusExistsTest() throws FoundException {
        ProjectStatus projectStatus = new ProjectStatus();
        projectStatus.setStatusId(1L);
        projectStatus.setStatus("RED");
        Optional<ProjectStatus> optionalProjectStatus = Optional.of(projectStatus);
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(projectStatusService.findProjectStatus(1L)).thenReturn(optionalProjectStatus);
        ResponseEntity<Response> responseEntity = projectStatusController.createProjectStatus(projectStatus);
    }
    @Test
    public void updateProjectStatusTest() throws NotFoundException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Optional<ProjectStatus> existingStatus =  Optional.of(getProjectStatus());
        when(projectStatusService.findProjectStatus(getProjectStatus().getStatusId())).thenReturn(existingStatus);
        Assert.assertEquals(projectStatusController.updateProjectStatus(new ProjectStatus()).getStatusCode(), HttpStatus.ACCEPTED);
    }

    @Test(expected = NotFoundException.class)
    public void updateProjectStatusNotFoundExceptionTest() throws FoundException, NotFoundException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Optional<ProjectStatus> existingStatus = Optional.empty();
        Assert.assertEquals(projectStatusController.updateProjectStatus(getProjectStatus()).getStatusCode(), HttpStatus.ACCEPTED);
    }
    @Test
    public void deleteProjectStatusTest() throws NotFoundException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Optional<ProjectStatus> existingProjectStatus =  Optional.of(getProjectStatus());
        Assert.assertEquals(projectStatusController.deleteProjectStatus(1L).getStatusCode(), HttpStatus.ACCEPTED);
    }


    private ProjectStatus getProjectStatus(){
        ProjectStatus projectStatus = new ProjectStatus("REDa");
        //projectStatus.setStatusId(1L);
       // projectStatus.setStatus("RED");
        return projectStatus;
    }
}

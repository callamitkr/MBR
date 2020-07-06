package com.capgemini.mbr.controller;

import com.capgemini.mbr.aspect.LoggingAspect;
import com.capgemini.mbr.bean.Response;
import com.capgemini.mbr.exception.DataFoundException;
import com.capgemini.mbr.exception.DataNotFoundException;
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

import java.util.ArrayList;
import java.util.List;
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
    public void createProjectStatusTest() throws DataFoundException {
        Optional<ProjectStatus> optionalProjectStatus = Optional.empty();
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(projectStatusService.createProjectStatus(any(ProjectStatus.class))).thenReturn(getProjectStatus());
        ResponseEntity<Response> responseEntity = projectStatusController.createProjectStatus(getProjectStatus());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
    }

    @Test(expected = DataFoundException.class)
    public void createProjectStatusWhenProjectStatusExistsTest() throws DataFoundException {
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
    public void updateProjectStatusTest() throws DataNotFoundException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Optional<ProjectStatus> existingStatus =  Optional.of(getProjectStatus());
        when(projectStatusService.findProjectStatus(getProjectStatus().getStatusId())).thenReturn(existingStatus);
        Assert.assertEquals(projectStatusController.updateProjectStatus(new ProjectStatus()).getStatusCode(), HttpStatus.ACCEPTED);
    }

    @Test(expected = DataNotFoundException.class)
    public void updateProjectStatusNotFoundExceptionTest() throws DataFoundException, DataNotFoundException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Optional<ProjectStatus> existingStatus = Optional.empty();
        Assert.assertEquals(projectStatusController.updateProjectStatus(getProjectStatus()).getStatusCode(), HttpStatus.ACCEPTED);
    }
    @Test
    public void deleteProjectStatusTest() throws DataNotFoundException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Optional<ProjectStatus> existingProjectStatus =  Optional.of(getProjectStatus());
        Assert.assertEquals(projectStatusController.deleteProjectStatus(1L).getStatusCode(), HttpStatus.ACCEPTED);
    }

    @Test
    public void getProjectStatusTest() throws DataNotFoundException {
        List<ProjectStatus> projectStatusList = new ArrayList<>();
        projectStatusList.add(getProjectStatus());
        when(projectStatusService.getProjectStatus()).thenReturn(projectStatusList);
        Assert.assertEquals(projectStatusController.getProjectStatus().getStatusCode(),HttpStatus.OK);
    }
    @Test(expected = DataNotFoundException.class)
    public void getBuDataNotFoundTest() throws DataNotFoundException {
        projectStatusController.getProjectStatus();
    }
    private ProjectStatus getProjectStatus(){
        ProjectStatus projectStatus = new ProjectStatus("RED");
        //projectStatus.setStatusId(1L);
       // projectStatus.setStatus("RED");
        return projectStatus;
    }
}

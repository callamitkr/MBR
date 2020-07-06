package com.capgemini.mbr.controller;

import com.capgemini.mbr.aspect.LoggingAspect;
import com.capgemini.mbr.bean.Response;
import com.capgemini.mbr.exception.DataFoundException;
import com.capgemini.mbr.exception.DataNotFoundException;
import com.capgemini.mbr.model.Bu;
import com.capgemini.mbr.model.Project;
import com.capgemini.mbr.service.ProjectService;
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
public class ProjectControllerTest {
    @InjectMocks
    ProjectController projectController;
    @Mock
    private ProjectService projectService;
    @Mock
    MessageSource messageSource;
    @Before
    public void beforeTest() {
        MockMvcBuilders.standaloneSetup(ProjectController.class)
                .setControllerAdvice(new LoggingAspect())
                .build();
    }
    @Test
    public void createBuTest() throws DataFoundException {
        Optional<Bu> optionalBu = Optional.empty();
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(projectService.createProject(any(Project.class))).thenReturn(getProject());
        ResponseEntity<Response> responseEntity = projectController.createProject(getProject());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
    }

    @Test(expected = DataFoundException.class)
    public void createBuWhenReportBuExistsTest() throws DataFoundException {
        Project project = new Project();
        project.setProjectId(1L);
        project.setBarclaysPm("pm");
        Optional<Project> optionalProject = Optional.of(project);
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(projectService.findProject(1L)).thenReturn(optionalProject);
        ResponseEntity<Response> responseEntity = projectController.createProject(project);
    }
    @Test
    public void updateBuTest() throws DataNotFoundException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Optional<Project> existingProject =  Optional.of(getProject());
        when(projectService.findProject(getProject().getProjectId())).thenReturn(existingProject);
        Assert.assertEquals(projectController.updateProject(new Project()).getStatusCode(), HttpStatus.ACCEPTED);
    }

    @Test(expected = DataNotFoundException.class)
    public void updateBuNotFoundExceptionTest() throws DataFoundException, DataNotFoundException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Optional<Project> existingProject = Optional.empty();
        Assert.assertEquals(projectController.updateProject(getProject()).getStatusCode(), HttpStatus.ACCEPTED);
    }
    @Test
    public void delteBuTest() throws DataNotFoundException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Optional<Project> existingProject =  Optional.of(getProject());
        Assert.assertEquals(projectController.deleteProject(1L).getStatusCode(), HttpStatus.ACCEPTED);
    }

    @Test
    public void getProjectTest() throws DataNotFoundException {
        List<Project> projectList = new ArrayList<>();
        projectList.add(getProject());
        when(projectService.getproject()).thenReturn(projectList);
        Assert.assertEquals(projectController.getProject().getStatusCode(),HttpStatus.OK);
    }
    @Test(expected = DataNotFoundException.class)
    public void getBuDataNotFoundTest() throws DataNotFoundException {
        projectController.getProject();
    }
    private Project getProject(){
        Project project = new Project("test","test desc","pm");
        return project;
    }
}

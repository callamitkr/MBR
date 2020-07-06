package com.capgemini.mbr.service;

import com.capgemini.mbr.model.Bu;
import com.capgemini.mbr.model.Project;
import com.capgemini.mbr.repository.ProjectRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ProjectServiceTest {
    @InjectMocks
    ProjectServiceImpl projectService;
    @Mock
    ProjectRepository projectRepository;


    @Test
    public void createProjectTest() {
        Project project = new Project("proj1","proj desc","pm");
        when(projectRepository.save(project)).thenReturn(project);
        Project createdProject =  projectService.createProject(project);
        assertEquals("pm", createdProject.getBarclaysPm());
        verify(projectRepository, times(1)).save(project);
    }

    @Test
    public void findProjectTest() {
        Project project = new Project("proj1","proj desc","pm");
       Optional<Project> optionalProject =  projectService.findProject(1L);
       assertEquals(optionalProject.isPresent(),false);
       verify(projectRepository, times(1)).findById(1L);
    }
    @Test
    public void deleteProjectTest() {
       Project project = new Project();
        project.setProjectId(3L);
        projectService.deleteProject(project.getProjectId());
        verify(projectRepository, times(1)).deleteById(eq(project.getProjectId()));
    }

    @Test
    public void getProjectTest(){
        List<Project> list = projectService.getproject();
        assertTrue( list.size() ==0);
        verify(projectRepository, times(1)).findAll();
    }
}

package com.capgemini.mbr.service;

import com.capgemini.mbr.model.ProjectStatus;
import com.capgemini.mbr.repository.ProjectStatusRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ProjectStatusServiceTest {
    @InjectMocks
    ProjectStatusServiceImpl projectStatusService;
    @Mock
    ProjectStatusRepository projectStatusRepository;


    @Test
    public void createProjectStatusTest() {
        ProjectStatus projectStatus = new ProjectStatus("RED");
        when(projectStatusRepository.save(projectStatus)).thenReturn(projectStatus);
        ProjectStatus createdProjectStatus =  projectStatusService.createProjectStatus(projectStatus);
        assertEquals("RED", createdProjectStatus.getStatus());
        verify(projectStatusRepository, times(1)).save(projectStatus);
    }

    @Test
    public void findProjectStatusTest() {
        ProjectStatus ProjectStatus = new ProjectStatus("UK");
        Optional<ProjectStatus> optionalProjectStatus =  projectStatusService.findProjectStatus(1L);
        assertEquals(optionalProjectStatus.isPresent(),false);
        verify(projectStatusRepository, times(1)).findById(1L);
    }
    @Test
    public void deleteProjectStatusTest() {
        ProjectStatus status= new ProjectStatus();
        status.setStatus("RED");
        status.setStatusId(1L);
        projectStatusService.deleteProjectStatus(status.getStatusId());
        verify(projectStatusRepository, times(1)).deleteById(eq(status.getStatusId()));
    }
}

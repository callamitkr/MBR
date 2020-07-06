package com.capgemini.mbr.report.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProjectStatusTest {

    @Test
    public void projectStatusConstructorTest(){
        ProjectStatus projectStatus = new ProjectStatus("RED");
        assertEquals(projectStatus.getStatus(),"RED");
    }
    @Test
    public void projectGetterSetterTest(){
        ProjectStatus projectStatus = new ProjectStatus();
        projectStatus.setStatus("RED");
        projectStatus.setStatusId(2L);
        assertEquals(projectStatus.getStatus(),"RED");
        assertEquals(projectStatus.getStatusId().longValue(),2);
        assertNotNull(projectStatus.toString());
    }

}

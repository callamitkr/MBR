package com.capgemini.mbr.report.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProjectTest {

    @Test
    public void projectConstructorTest(){
        Project project = new Project("Easy pay","Easy pay desc","amit");
        assertEquals(project.getBarclaysPm(),"amit");
    }
    @Test
    public void projectGetterSetterTest(){
        Project project = new Project();
        project.setProjectId(2L);
        project.setProjectName("Easy");
        project.setProjectDesc("Easy desc");
        project.setBarclaysPm("ram");
        project.setBu(new Bu("US"));
        project.setPhase(new Phase("Dev"));
        assertEquals(project.getProjectId().longValue(),2);
        assertEquals(project.getBarclaysPm(),"ram");
        assertEquals(project.getProjectName(),"Easy");
        assertEquals(project.getProjectDesc(),"Easy desc");
        assertEquals(project.getBu().getBu(),"US");
        assertEquals(project.getPhase().getPhase(),"Dev");
        assertNotNull(project.toString());


    }
}

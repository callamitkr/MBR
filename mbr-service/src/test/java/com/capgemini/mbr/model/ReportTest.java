package com.capgemini.mbr.model;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ReportTest {

    @Test
    public void reportGetterSetterTest(){
        Report report = new Report();
        report.setKeyHighlights("key");
        report.setBarclaysFeedback("ok");
        report.setIssueRoadBlock("no");
        report.setCreatedBy("amit");
        report.setUpdatedDate(LocalDate.now());
        report.setProject(new Project("Easy pay","Easy pay desc","Ram"));
        report.setProjectStatus(new ProjectStatus("RED"));
        assertEquals(report.getKeyHighlights(),"key");
        assertEquals(report.getBarclaysFeedback(),"ok");
        assertEquals(report.getIssueRoadblock(),"no");
        assertEquals(report.getCreatedBy(),"amit");
        assertEquals(report.getUpdatedDate(),LocalDate.now());
        assertEquals(report.getProject().getBarclaysPm(),"Ram");
        assertEquals(report.getProjectStatus().getStatus(),"RED");
        assertNotNull(report.toString());

    }
}

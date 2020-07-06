package com.capgemini.mbr.report.model;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ReportTest {

    @Test
    public void reportGetterSetterTest(){
        Report report = new Report();
        report.setReportId(2l);
        report.setKeyHighlights("key");
        report.setBarclaysFeedback("ok");
        report.setIssueRoadBlock("no");
        report.setCreatedBy("amit");
        report.setCreatedDate(LocalDate.now());
        report.setUpdatedDate(LocalDate.now());
        report.setProject(new Project("Easy pay","Easy pay desc","Ram"));
        report.setProjectStatus(new ProjectStatus("RED"));
        assertEquals(report.getReportId().longValue(),2L);
        assertEquals(report.getKeyHighlights(),"key");
        assertEquals(report.getBarclaysFeedback(),"ok");
        assertEquals(report.getIssueRoadblock(),"no");
        assertEquals(report.getCreatedBy(),"amit");
        assertEquals(report.getCreatedDate(),LocalDate.now());
        assertEquals(report.getUpdatedDate(),LocalDate.now());
        assertEquals(report.getProject().getBarclaysPm(),"Ram");
        assertEquals(report.getProjectStatus().getStatus(),"RED");
        assertNotNull(report.toString());
    }
}

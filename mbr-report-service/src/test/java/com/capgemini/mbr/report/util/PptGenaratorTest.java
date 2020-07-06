package com.capgemini.mbr.report.util;

import com.capgemini.mbr.report.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;
@RunWith(MockitoJUnitRunner.class)
public class PptGenaratorTest {
    @InjectMocks
    PptGenerator pptGenerator;
    @Mock
    ByteArrayInputStream byteArrayInputStream;
    @Mock
    DateUtil dateUtil;
    @Test
    public void generatePptTest() throws IOException {
        byteArrayInputStream = pptGenerator.generatePpt(getReportList());
        assertTrue(byteArrayInputStream.markSupported());
}
    private List<Report> getReportList(){
        List<Report> reportList = new ArrayList<>();
        Report report = new Report("Easy pay", "good", "no", "amit", LocalDate.now(), LocalDate.now());
        Project project = new Project("Project1","prject desc","pm");
        project.setBu(new Bu("US"));
        project.setPhase(new Phase("Dev"));
        ProjectStatus projectStatus = new ProjectStatus("Red");
        report.setProject(project);
        report.setProjectStatus(projectStatus);
        reportList.add(report);
        return  reportList;
    }


}

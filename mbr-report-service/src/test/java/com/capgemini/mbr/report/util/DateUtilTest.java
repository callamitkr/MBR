package com.capgemini.mbr.report.util;

import com.google.inject.Inject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
@RunWith(MockitoJUnitRunner.class)
public class DateUtilTest {
    @InjectMocks
    DateUtil dateUtil;
    @Test
    public void getCurrentMontYearTest(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM yyyy");
        assertThat(dateUtil.getMontYearPattern(07,2020,"MMM yyyy")).isEqualTo(formatter.format(LocalDate.of(2020,07,01)).toString()) ;
    }
}

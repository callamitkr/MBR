package com.capgemini.mbr.util;

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
        assertThat(dateUtil.getCurrentMontYear("MMM yyyy")).isEqualTo(formatter.format(LocalDate.now()).toString()) ;
    }
}

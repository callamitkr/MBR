package com.capgemini.mbr.service;

import com.capgemini.mbr.model.Bu;
import com.capgemini.mbr.model.Report;
import com.capgemini.mbr.repository.BuRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class BuServiceTest {
    @InjectMocks
    BuServiceImpl buService;
    @Mock
    BuRepository buRepository;


    @Test
    public void createBuTest() {
        Bu bu = new Bu("UK");
        when(buRepository.save(bu)).thenReturn(bu);
        Bu createdBu =  buService.createBu(bu);
        assertEquals("UK", createdBu.getBu());
        verify(buRepository, times(1)).save(bu);
    }

    @Test
    public void findBuTest() {
        Bu bu = new Bu("UK");
       Optional<Bu> optionalBu =  buService.findBu(1L);
       assertEquals(optionalBu.isPresent(),false);
       verify(buRepository, times(1)).findById(1L);
    }
    @Test
    public void deleteBuTest() {
        Bu bu= new Bu();
        bu.setBu("US");
        bu.setBuId(1L);
        buService.deleteBu(bu.getBuId());
        verify(buRepository, times(1)).deleteById(eq(bu.getBuId()));
    }
    @Test
    public void getBuTest(){
       List<Bu> list = buService.getBu();
        assertTrue( list.size() ==0);
        verify(buRepository, times(1)).findAll();
    }



}

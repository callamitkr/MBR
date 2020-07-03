package com.capgemini.mbr.service;

import com.capgemini.mbr.model.Bu;
import com.capgemini.mbr.model.Phase;
import com.capgemini.mbr.repository.PhaseRepository;
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
public class PhaseServiceTest {
    @InjectMocks
    PhaseServiceImpl phaseService;
    @Mock
    PhaseRepository phaseRepository;


    @Test
    public void createPhaseTest() {
        Phase phase = new Phase("Dev");
        when(phaseRepository.save(phase)).thenReturn(phase);
        Phase createdPhase =  phaseService.createPhase(phase);
        assertEquals("Dev", createdPhase.getPhase());
        verify(phaseRepository, times(1)).save(phase);
    }

    @Test
    public void findPhaseTest() {
        Phase phase = new Phase("UK");
       Optional<Phase> optionalBu =  phaseService.findPhase(1L);
       assertEquals(optionalBu.isPresent(),false);
       verify(phaseRepository, times(1)).findById(1L);
    }
    @Test
    public void deletePhaseTest() {
        Phase phase= new Phase();
       phase.setPhaseId(2L);
       phase.setPhase("Dev");
        phaseService.deletePhase(phase.getPhaseId());
        verify(phaseRepository, times(1)).deleteById(eq(phase.getPhaseId()));
    }
}

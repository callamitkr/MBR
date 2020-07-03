package com.capgemini.mbr.model;

import org.junit.Test;
import static  org.junit.Assert.assertEquals;
public class PhaseTest {

    @Test
    public void phaseIdTest(){
        Phase phase = new Phase();
        phase.setPhaseId(1L);
        assertEquals(phase.getPhaseId().longValue(),1L);
    }
    @Test
    public void phaseTest(){
        Phase phase = new Phase();
        phase.setPhase("phase1");
        assertEquals(phase.getPhase(),"phase1");
    }
    @Test
    public void phaseConstructorTest(){
        Phase phase = new Phase("phase1");
        assertEquals(phase.getPhase(),"phase1");
    }
}

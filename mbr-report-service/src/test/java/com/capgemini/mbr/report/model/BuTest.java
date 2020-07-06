package com.capgemini.mbr.report.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BuTest {

    @Test
    public void buIdTest(){
        Bu bu = new Bu();
        bu.setBuId(1L);
        assertEquals(bu.getBuId().longValue(),1L);
    }
    @Test
    public void buTest(){
        Bu bu = new Bu();
        bu.setBu("UK");
        assertEquals(bu.getBu(),"UK");
        assertNotNull(bu.toString());
    }
    @Test
    public void buConstructorTest(){
        Bu bu = new Bu("US");
        assertEquals(bu.getBu(),"US");


    }
}

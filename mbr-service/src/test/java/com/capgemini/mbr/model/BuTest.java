package com.capgemini.mbr.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
    }
    @Test
    public void buConstructorTest(){
        Bu bu = new Bu("US");
        assertEquals(bu.getBu(),"US");
    }
}

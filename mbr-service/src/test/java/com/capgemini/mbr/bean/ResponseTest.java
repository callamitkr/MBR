package com.capgemini.mbr.bean;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.assertEquals;
public class ResponseTest {
    @Test
    public void responseGetterSetterTest(){
        Response response =  new Response();
        response.setMessage("created");
        response.setStatusCode(HttpStatus.CREATED);
        assertEquals(response.getMessage(),"created");
        assertEquals(response.getStatus().value(),201);
    }
}

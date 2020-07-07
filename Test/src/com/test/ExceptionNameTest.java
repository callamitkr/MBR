package com.test;

import java.time.DateTimeException;
import java.time.LocalDate;

public class ExceptionNameTest {
	public static void main (String [] str) {
		try {
			LocalDate.of(2020, 13, 1);
		
		} catch (DateTimeException e) {
			    
			   System.out.println(e.getMessage() +"--"+e.getClass().getSimpleName() );
		}
		
	}

}

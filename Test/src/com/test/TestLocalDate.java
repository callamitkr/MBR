package com.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestLocalDate {

	public static void main(String[] args) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-yyyy");
         String  monthYear = formatter.format(LocalDate.now()).toString();
        System.out.println(monthYear);
	}

}

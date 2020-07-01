package com.capgemini.mbr.report.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
	public String getCurrentMontYear(String pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return formatter.format(LocalDate.now()).toString();
	}
   
}

package com.capgemini.mbr.report.util;

import com.capgemini.mbr.report.exception.DataNotFoundException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
	private DateTimeFormatter formatter;

	public String getMontYearPattern(int month, int year, String pattern) {
		formatter = DateTimeFormatter.ofPattern(pattern);
		return formatter.format(LocalDate.of(year, month, 1));
	}

}
package br.com.alelo.consumer.consumerpat.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormatDateUtil {
	
	private static final String BRAZILIAN_DATE_PATTERN = "dd/MM/yyyy";
	
	public static LocalDate stringToLocalDate(String localDate) {
		return LocalDate.parse(localDate, DateTimeFormatter.ofPattern(BRAZILIAN_DATE_PATTERN));
	}
	public static String localDateToString(LocalDate localDate) {
		return localDate.format(DateTimeFormatter.ofPattern(BRAZILIAN_DATE_PATTERN));
	}
	
}

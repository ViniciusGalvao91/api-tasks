package com.api.tasks.Utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TaskUtils {

	public static LocalDate stringToLocalDate(String date) {

		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return LocalDate.parse(date, formatDate);
	}

	public static LocalTime stringToLocalTime(String time) {

		DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss");
		return LocalTime.parse(time, formatTime);
	}

	public static String LocalDateToString(LocalDate date) {

		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return date.format(formatDate);
	}

	public static String LocalTimeToString(LocalTime time) {

		DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss");
		return time.format(formatTime);
	}

}

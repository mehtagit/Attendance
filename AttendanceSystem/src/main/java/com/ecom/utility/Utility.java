package com.ecom.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class Utility {
	final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	final SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy-MM-");
	final SimpleDateFormat salaryIdFormat = new SimpleDateFormat("yyyyMM-");

	public int getDaysBetweenDates(String toDate, String fromDate) {
		Date date1 = null;
		Date date2 = null;
		int noofdays = 0;
		try {
			date1 = (Date) dateFormat.parse(fromDate);
			date2 = (Date) dateFormat.parse(toDate);
			long diff = date1.getTime() - date2.getTime();
			noofdays = (int) (diff / (1000 * 24 * 60 * 60));
		} catch (Exception e) {

		}
		return noofdays;
	}

	public String getTommorowDate(int days) {

		final Date date = new Date();
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, days);
		return dateFormat.format(calendar.getTime()) + " 00:00:00";
	}

	public String getMonthStartDate(int month) {

		final Date date = new Date();
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, month);
		calendar.set(Calendar.DATE, 1);
		return dateFormat.format(calendar.getTime()) + " 00:00:00";
	}

	public String getMonth(int month) {

		final Date date = new Date();
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, month);
		return salaryIdFormat.format(calendar.getTime());
	}

	public String getCurrentMonthStartDate() {
		final Date date = new Date();
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return monthFormat.format(calendar.getTime()) + "01 00:00:00";
	}
}

package com.ecom.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTesting {

	public static void main(String[] args) {
		
		System.out.println(getDayOfWeek("2017-06-20 32"));
	}
	
	public static int getDayOfWeek(String dateTime){
		int day = 0;
		try{
		Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(dateTime.split(" ")[0]);
		Calendar cal  = Calendar.getInstance();
		cal.setTime(date1);
		day  =cal.get(Calendar.DAY_OF_WEEK);
		}catch(Exception e){
			e.printStackTrace();
		}
		return day;
	}
}

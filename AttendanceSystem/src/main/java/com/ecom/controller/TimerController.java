package com.ecom.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/TimerService")
public class TimerController {

	private static SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");

	@RequestMapping(value = "/getTimerDate/{day}",  method = RequestMethod.GET)
	@ResponseBody
	public String getDate(@PathVariable("day") String day) {
		int days = 0;
		try{
			days = Integer.parseInt(day);
		}catch (Exception e) {
		}
		return getDate(days);
	}
	
	@RequestMapping(value = "/getTimerDate",  method = RequestMethod.GET)
	@ResponseBody
	public String getCurrentDate() {
		return getDate(0);
	}
	public String getDate(int day) {
		final Date date = new Date();
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, day);
		return dateformat.format(calendar.getTime());
	}
	
	
	
}

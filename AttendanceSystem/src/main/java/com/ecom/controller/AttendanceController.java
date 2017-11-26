package com.ecom.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.atttendance.pojos.Attendance;
import com.atttendance.pojos.Labour;
import com.ecom.services.AttendanceService;
import com.google.gson.Gson;

@Controller
@RequestMapping("/AttendanceService")
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;
	
	@Autowired
	Gson gson = null;

	@RequestMapping(value = "/attendance/{industry_id}",  method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public String markAttendance(@PathVariable("industry_id") String industry_id,@RequestBody String JSON, HttpServletRequest request) {
		System.out.println("markAttendance : "+JSON);
		Attendance attendance = gson.fromJson(JSON, Attendance.class);
		attendance.setIndustry_id(industry_id);
		String result = gson.toJson(attendanceService.markAttendance(attendance));
		System.out.println("Result Return : "+result);
		return result;
	}
	
	@RequestMapping(value = "/attendanceSummary/{industry_id}",  method = RequestMethod.POST)
	@ResponseBody
	public String labourSummary(@RequestBody String JSON, HttpServletRequest request,@PathVariable("industry_id") String industry_id) {
		System.out.println("Request For Attendance History JSON Recieved "+JSON+", FROM IP"+request.getRemoteHost());
		Labour labour = gson.fromJson(JSON, Labour.class);
		JSON = gson.toJson(attendanceService.makeSummary(labour));
		System.out.println("Response: "+JSON);
		return JSON;
	}
	
	
	@RequestMapping(value = "/refresh/{industry_id}", method = RequestMethod.GET)
	@ResponseBody
	public String refresh(@PathVariable("industry_id") String industry_id) {
		List<Attendance> attendances = attendanceService.refresh(industry_id);
		
		String json =  gson.toJson(attendances);
		System.out.println("refresh Response: "+json);
		return json;
	}
	
}

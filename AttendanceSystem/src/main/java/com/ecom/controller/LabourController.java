package com.ecom.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atttendance.pojos.Labour;
import com.ecom.services.AttendanceService;
import com.ecom.services.LabourService;
import com.ecom.services.SalaryService;
import com.google.gson.Gson;

@Controller
@RequestMapping("/LabourService")
public class LabourController {

	@Autowired
	LabourService labourService;
	
	@Autowired
	SalaryService salaryService;
	@Autowired
	AttendanceService attendanceService;
	
	@Autowired
	Gson gson = null;

	@RequestMapping(value = "/list/{industry_id}", method = RequestMethod.GET)
	@ResponseBody
	public String list(@PathVariable("industry_id") String industry_id) {
		List<Labour> labourList = labourService.getLabourList(industry_id);
		
		for(Labour lb : labourList){
			lb.setAttendance(attendanceService.getCurrentAttendance(lb));
			salaryService.fillAdminDetails(lb);
		}
		String json =  gson.toJson(labourList);
		System.out.println("Response: "+json);
		return json;
	}
	
	@RequestMapping(value = "/addLabour/{industry_id}",  method = RequestMethod.POST)
	@ResponseBody
	public String add(@RequestBody String JSON, HttpServletRequest request,@PathVariable("industry_id") String industry_id) {
		
		try
		{
			System.out.println("Request For New Resource Recieved "+JSON+", FROM IP"+request.getRemoteHost());
		
		Labour labour = gson.fromJson(JSON, Labour.class);
		JSON = gson.toJson(labourService.addLabour(labour,industry_id));
		System.out.println("Response: "+labour);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return JSON;
	}
	
	@RequestMapping(value = "/updateLabour/{industry_id}",  method = RequestMethod.POST)
	@ResponseBody
	public String update(@RequestBody String JSON, HttpServletRequest request,@PathVariable("industry_id") String industry_id) {
		System.out.println("Request For Update Resource Recieved "+JSON+", FROM IP"+request.getRemoteHost());
		Labour labour = gson.fromJson(JSON, Labour.class);
		JSON = gson.toJson(labourService.updateLabour(labour,industry_id));
		System.out.println("Response: "+labour);
		return JSON;
	}
}

package com.ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atttendance.pojos.Labour;
import com.atttendance.pojos.Salary;
import com.ecom.base.SalaryCalculator;
import com.ecom.services.LabourService;
import com.ecom.services.SalaryService;
import com.ecom.utility.ApplicationContextProvider;
import com.google.gson.Gson;

@Controller
@RequestMapping("/SalaryService")
public class SalaryController {

	@Autowired
	SalaryService salaryService;

	@Autowired
	LabourService labourService;
	
	SalaryCalculator salaryCalculator;
	
	@Autowired
	Gson gson = null;

	@RequestMapping(value = "/fullAndFinalCalculate/{industry_id}", method = RequestMethod.POST)
	@ResponseBody
	public String fullAndFinalCalculate(@RequestBody String JSON, @PathVariable("industry_id") String industry_id) {
		System.out.println("fullAndFinal JSON (" + JSON + ")");
		Labour labour 	= gson.fromJson(JSON, Labour.class);
		String json 	= gson.toJson(salaryCalculator.fullAndFinalCalculate(labour));
		System.out.println("Response : " + json);
		return json;
	}
	
	@RequestMapping(value = "/calculate/{industry_id}", method = RequestMethod.POST)
	@ResponseBody
	public String calculateSalary(@RequestBody String JSON, @PathVariable("industry_id") String industry_id) {
		System.out.println("Calculate JSON (" + JSON + ")");
		Labour labour 	= gson.fromJson(JSON, Labour.class);
		String json 	= gson.toJson(ApplicationContextProvider.getBean(industry_id , SalaryCalculator.class).calculateSalary(labour));
		System.out.println("Response : " + json);
		return json;
	}

	@RequestMapping(value = "/paySalary/{industry_id}", method = RequestMethod.POST)
	@ResponseBody
	public String paySalary(@RequestBody String JSON, @PathVariable("industry_id") String industry_id) {
		System.out.println("calculate JSON (" + JSON + ")");
		Salary salary = gson.fromJson(JSON, Salary.class);
		String json = gson.toJson(salaryService.updateSalary(salary));
		System.out.println("Response : " + json);
		return json;
	}
	
	@RequestMapping(value = "/fullAndFinal/{industry_id}", method = RequestMethod.POST)
	@ResponseBody
	public String fullAndFinal(@RequestBody String JSON, @PathVariable("industry_id") String industry_id) {
		System.out.println("calculate JSON (" + JSON + ")");
		Labour labour = gson.fromJson(JSON, Labour.class);
		String json = gson.toJson(salaryService.insertSalary(labour.getSalary()));
		json = gson.toJson(labourService.delete(labour));
		System.out.println("Response : " + json);
		return json;
	}
}

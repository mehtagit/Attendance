package com.ecom.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atttendance.pojos.Industry;
import com.ecom.services.IndustryService;
import com.google.gson.Gson;

@Controller
@RequestMapping("/Register")
public class RegistrationController {

	@Autowired
	IndustryService hotelService;
	
	@Autowired
	Gson gson = null;

	@RequestMapping(value = "/industry",  method = RequestMethod.POST)
	@ResponseBody
	public String createOrder(@RequestBody String JSON, HttpServletRequest request) {
		System.out.println("Request For Order JSON Recieved "+JSON+", FROM IP"+request.getRemoteHost());
		Enumeration headerNames = request.getHeaderNames();
		String Allheaders = "";
		while(headerNames.hasMoreElements()) {
		  String headerName = (String)headerNames.nextElement();
		  Allheaders = Allheaders + headerName +"["+ request.getHeader(headerName)+"]";
		}
		System.out.println("headers : "+ Allheaders);
		
		Industry industry = gson.fromJson(JSON, Industry.class);
		JSON = gson.toJson(hotelService.getIndustryDetail(industry));
		System.out.println("Response: "+JSON);
		return JSON;
	}
	
}

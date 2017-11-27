package com.ecom.controller;

import java.util.List;

import javax.servlet.jsp.jstl.core.Config;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.atttendance.pojos.AttendanceHistory;
import com.atttendance.pojos.Industry;
import com.atttendance.pojos.Labour;
import com.atttendance.pojos.Payment;
import com.ecom.util.RestUrls;
import com.ecom.util.Utility;
import com.google.gson.reflect.TypeToken;

@Controller
@SessionAttributes("industry")
public class AttendanceController {

	@Autowired
	private Utility utility;

	@RequestMapping("/attendanceSummary/{labour_id}")
	public String employees(@PathVariable("labour_id") String labour_id,
			@ModelAttribute("industry") Industry industry, Model model) {
		Labour labour = new Labour();
		labour.setLabour_id(labour_id);
		labour.setIndustry_id(industry.getIndustry_id());
		
		String labour_Json = utility.gson.toJson(labour);
		String response = utility.callPost(RestUrls.attendanceHistoryURL+industry.getIndustry_id(), labour_Json);
		
		
		if (response == null || "null".equals(response)) {
			System.out.println(" Server is not responding..");
			model.addAttribute("message", "Payment is not Saved. Please try after some time");
			return "error";
		} else {
			AttendanceHistory attendanceHistory = utility.gson.fromJson(response, AttendanceHistory.class);
			model.addAttribute("message", response);
			return "error";
			//model.addAttribute("attendanceHistory", attendanceHistory);
		}
		//return "formAtendanceHistory";
	}
}
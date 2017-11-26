package com.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.atttendance.pojos.Industry;
import com.atttendance.pojos.Labour;
import com.ecom.util.RestUrls;
import com.ecom.util.Utility;
import com.google.gson.reflect.TypeToken;

@Controller
@SessionAttributes("industry")
public class WelcomeController {

	@Autowired
	private Utility utility;

	// inject via application.properties
	/*
	 * @Value("${welcome.message:test}") private String message = "Hello World";
	 */
	@RequestMapping("/")
	public String welcome(Model model) {
		try {
		model.addAttribute("industryForm", new Industry());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("industryForm") Industry industry, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {

		String Industry_Json = utility.gson.toJson(industry);
		String response = utility.callPost(RestUrls.registerURL, Industry_Json);
		if (response == null || "null".equals(response)) {
			System.out.println(" Server is not responding..");
			model.addAttribute("message", "Server is not responding..");
			return "errorLogin";
		} else {
			Industry industryResp = utility.gson.fromJson(response, Industry.class);
			
			System.out.println("industryResp :" + industryResp);
			if (industryResp.getStatus() == 1) {
				model.addAttribute("message", "Successfully login by " + industryResp.getIndustry_id());
				model.addAttribute("industry",industryResp);
				return "uploadAttendance";
			} else if (industryResp.getStatus() < 0) {
				model.addAttribute("message", "You are not registered with us.");
				return "errorLogin";
			} else {
				model.addAttribute("message", "Contact Customer Care.");
				return "errorLogin";
			}
		}
	}

	@RequestMapping("/employees")
	public String employees(@ModelAttribute("industry") Industry industry, Model model) {

		String labourListResponseInJson = utility.callGet(RestUrls.getLabourListURL + industry.getIndustry_id());
	
		if (labourListResponseInJson == null || "null".equals(labourListResponseInJson)) {
			System.out.println(" Server is not responding..");
			model.addAttribute("message", "Server is not responding..");
			return "errorLogin";
		} else {
			List<Labour> labourList = utility.gson.fromJson(labourListResponseInJson, new TypeToken<List<Labour>>() {
			}.getType());
			model.addAttribute("labourList", labourList);
		}
		return "employees";
	}
	
	@RequestMapping("/attendance")
	public String attendance(@ModelAttribute("industry") Industry industry, Model model) {

		String labourListResponseInJson = utility.callGet(RestUrls.getLabourListURL + industry.getIndustry_id());
	
		if (labourListResponseInJson == null || "null".equals(labourListResponseInJson)) {
			System.out.println(" Server is not responding..");
			model.addAttribute("message", "Server is not responding..");
			return "errorLogin";
		} else {
			List<Labour> labourList = utility.gson.fromJson(labourListResponseInJson, new TypeToken<List<Labour>>() {
			}.getType());
			model.addAttribute("labourList", labourList);
		}
		return "attendance";
	}
	
	@RequestMapping("/salary")
	public String salary(@ModelAttribute("industry") Industry industry, Model model) {

		String labourListResponseInJson = utility.callGet(RestUrls.getLabourListURL + industry.getIndustry_id());
	
		if (labourListResponseInJson == null || "null".equals(labourListResponseInJson)) {
			System.out.println(" Server is not responding..");
			model.addAttribute("message", "Server is not responding..");
			return "errorLogin";
		} else {
			List<Labour> labourList = utility.gson.fromJson(labourListResponseInJson, new TypeToken<List<Labour>>() {
			}.getType());
			model.addAttribute("labourList", labourList);
		}
		return "salary";
	}
	
	@RequestMapping("/advancePayment")
	public String advancePayment(@ModelAttribute("industry") Industry industry, Model model) {

		String labourListResponseInJson = utility.callGet(RestUrls.getLabourListURL + industry.getIndustry_id());
	
		if (labourListResponseInJson == null || "null".equals(labourListResponseInJson)) {
			System.out.println(" Server is not responding..");
			model.addAttribute("message", "Server is not responding..");
			return "errorLogin";
		} else {
			List<Labour> labourList = utility.gson.fromJson(labourListResponseInJson, new TypeToken<List<Labour>>() {
			}.getType());
			model.addAttribute("labourList", labourList);
		}
		return "advancePayment";
	}
	
	@RequestMapping("/fullAndFinal")
	public String fullAndFinal(@ModelAttribute("industry") Industry industry, Model model) {

		String labourListResponseInJson = utility.callGet(RestUrls.getLabourListURL + industry.getIndustry_id());
	
		if (labourListResponseInJson == null || "null".equals(labourListResponseInJson)) {
			System.out.println(" Server is not responding..");
			model.addAttribute("message", "Server is not responding..");
			return "errorLogin";
		} else {
			List<Labour> labourList = utility.gson.fromJson(labourListResponseInJson, new TypeToken<List<Labour>>() {
			}.getType());
			model.addAttribute("labourList", labourList);
		}
		return "fullAndFinal";
	}
}
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

import com.atttendance.pojos.Industry;
import com.atttendance.pojos.Labour;
import com.atttendance.pojos.Payment;
import com.ecom.util.RestUrls;
import com.ecom.util.Utility;
import com.google.gson.reflect.TypeToken;

@Controller
@SessionAttributes("industry")
public class PayAdvanceController {

	@Autowired
	private Utility utility;

	@RequestMapping(value = "/savePayment", method = RequestMethod.POST)
	public String login(@ModelAttribute("paymentForm") Payment payment, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {

		String payment_Json = utility.gson.toJson(payment);
		String response = utility.callPost(RestUrls.advancePayURL+payment.getIndustry_id(), payment_Json);
		
		System.out.println("payment_Json: " +payment_Json);
		
		if (response == null || "null".equals(response)) {
			System.out.println(" Server is not responding..");
			model.addAttribute("message", "Payment is not Saved. Please try after some time");
			return "error";
		} else {
			Payment paymentResponse = utility.gson.fromJson(response, Payment.class);
			
			if (paymentResponse.isStatus()) {
				model.addAttribute("message","You have Successfully given rupees " + paymentResponse.getAmount() + " to ["+response+"]");
				return "error";
            } else {
            	model.addAttribute("message", "Payment is not Saved. Please try after some time ["+response+"]");
    			return "error";
                
            }
		}
	}

	@RequestMapping("/formPayAdvance/{labour_id}/{firstname}")
	public String employees(@PathVariable("labour_id") String labour_id, @PathVariable("firstname") String firstname,
			@ModelAttribute("industry") Industry industry, Model model) {
		Payment payment = new Payment();
		payment.setLabour_id(labour_id);
		payment.setIndustry_id(industry.getIndustry_id());
		model.addAttribute("paymentForm", payment);
		return "formPayAdvance";
	}

	/*@RequestMapping("/listAttendance")
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
		return "listAttendance";
	}

	@RequestMapping("/listSalary")
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
		return "listSalary";
	}

	@RequestMapping("/listAdvancePayment")
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
		return "listAdvancePayment";
	}

	@RequestMapping("/listFullAndFinal")
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
		return "listFullAndFinal";
	}*/
}
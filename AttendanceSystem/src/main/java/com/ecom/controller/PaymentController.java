package com.ecom.controller;

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

import com.atttendance.pojos.Payment;
import com.ecom.services.PaymentService;
import com.google.gson.Gson;

@Controller
@RequestMapping("/PaymentService")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	Gson gson = null;

	@RequestMapping(value = "/giveAdvance/{industry_id}",   method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public String giveAdvance(@PathVariable("industry_id") String industry_id,@RequestBody String JSON, HttpServletRequest request) {
		System.out.println("markAttendance : "+JSON);
		Payment payment = gson.fromJson(JSON, Payment.class);
		payment.setIndustry_id(industry_id);
		String result = gson.toJson(paymentService.giveAdvance(payment));
		System.out.println("Result Return : "+result);
		return result;
	}
	
	/*@RequestMapping(value = "/getPaymentList/{industry_id}", method = RequestMethod.GET)
	@ResponseBody
	public String loginMethod(@PathVariable("industry_id") String industry_id) {
		String json = gson.toJson(paymentService.getMonthlyPaymentList(industry_id));
		return json;
	}
	*/
}

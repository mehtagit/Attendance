package com.ecom.base;

import org.springframework.beans.factory.annotation.Autowired;

import com.atttendance.pojos.Labour;
import com.atttendance.pojos.Salary;
import com.ecom.services.PaymentService;
import com.ecom.services.SalaryService;
import com.ecom.utility.Utility;

public abstract class SalaryCalculator {
	@Autowired
	protected SalaryService salaryService;

	@Autowired
	protected PaymentService paymentService;

	@Autowired
	protected Utility utility;

	public abstract Salary fullAndFinalCalculate(Labour labour);

	public abstract Salary calculate(Labour labour, String fromDate, String toDate);

	public abstract Salary calculateSalary(Labour labour);
}

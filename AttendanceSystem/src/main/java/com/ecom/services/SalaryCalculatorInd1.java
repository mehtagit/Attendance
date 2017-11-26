package com.ecom.services;

import org.springframework.stereotype.Component;

import com.atttendance.pojos.Labour;
import com.atttendance.pojos.Salary;
import com.ecom.base.SalaryCalculator;

@Component("IND1")
public class SalaryCalculatorInd1 extends SalaryCalculator {

	@Override
	public Salary calculateSalary(Labour labour) {
		String fromDate = utility.getMonthStartDate(-1);
		String toDate = utility.getCurrentMonthStartDate();

		String salaryId = utility.getMonth(-1) + labour.getLabour_id();
		Salary salary = salaryService.getPreviousCalculatedSalary(labour, salaryId);
		if (salary == null) {
			salary = calculate(labour, fromDate, toDate);
			salary.setId(salaryId);
			salaryService.insertSalary(salary);
		}

		return salary;
	}

	@Override
	public Salary fullAndFinalCalculate(Labour labour) {
		String fromDate = utility.getCurrentMonthStartDate();
		String toDate = utility.getTommorowDate(0);
		Salary salary = calculate(labour, fromDate, toDate);
		return salary;
	}

	public Salary calculate(Labour labour, String fromDate, String toDate) {
		Salary salary = null;
		try {
			int totalDays = utility.getDaysBetweenDates(fromDate, toDate);

			salaryService.fillAdminDetails(labour);
			salary = salaryService.getAttendanceSummary(labour, fromDate, toDate);
			// int HolidaysCount =
			// salaryService.getHolidaysCount(labour.getIndustry_id(), fromDate,
			// toDate);
			paymentService.fillMonthPaymentSummary(labour, salary, fromDate, toDate);

			// Calculating perday salary and perhour
			float salaryInDay = labour.getAdminDetail().getTotal_salary() / (float) 30;
			float salaryInHours = salaryInDay / (float) labour.getAdminDetail().getWorking_hours_in_day();

			salary.setFrom_date(fromDate);
			salary.setTo_date(toDate);

			salary.setTotal_days(totalDays + 1);
			salary.setSalary_in_day(salaryInDay);

			// Total Hours worked
			// int holidaysAmount = (int)(HolidaysCount * salaryInDay);
			int presentAmount = (int) (salary.getTotal_hours_worked() * salaryInHours);
			int overTimeAmount = (int) (salary.getOvertime_hours() * salaryInHours);
			salary.setTotal_salary(presentAmount);
			// Overtime Calculation
			salary.setOvertime_amount(overTimeAmount);

			// salary.setAbsent_amount(salary.getAbsent() * salaryInDay);

			// salary.setNet_salary(salary.getTotal_salary() -
			// (salary.getAbsent_amount() + salary.getAdvance_amount()));

			salary.setNet_salary((salary.getTotal_salary() + salary.getOvertime_amount()) - salary.getAdvance_amount());

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Returning Salary " + salary);
		return salary;

	}
}

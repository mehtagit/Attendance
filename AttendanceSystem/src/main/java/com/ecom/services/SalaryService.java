package com.ecom.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.atttendance.pojos.AdminDetail;
import com.atttendance.pojos.Attendance;
import com.atttendance.pojos.Labour;
import com.atttendance.pojos.Payment;
import com.atttendance.pojos.Salary;
import com.ecom.mappers.AdminMapper;
import com.ecom.mappers.AttendanceMapper;
import com.ecom.mappers.SalaryMapper;

@Service
public class SalaryService {

	@Autowired
	private JDBCTemplateService jdbcService;

	@Autowired
	PaymentService paymentService;

	public Labour fillAdminDetails(Labour labour) {
		long start = System.currentTimeMillis();
		String query = null;
		try {
			query = "select * from admin_detail where industry_id='" + labour.getIndustry_id() + "' and labour_id='"
					+ labour.getLabour_id() + "'";
			AdminDetail adminDetail = jdbcService.geJdbcTemplate().queryForObject(query, new AdminMapper());
			labour.setAdminDetail(adminDetail);
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Query : " + query);
		System.out.println(
				"SalaryService.fillAdminDetails took time in millisec:" + (System.currentTimeMillis() - start));
		return labour;
	}
	
	public Salary getPreviousCalculatedSalary(Labour labour,String salaryId) {
		long start = System.currentTimeMillis();
		String query = null;
		Salary salary = null;
		try {
			query = "select * from salary_book where industry_id='" + labour.getIndustry_id() + "' and labour_id='"
					+ labour.getLabour_id() + "' and id='"+salaryId+"'";
			salary = jdbcService.geJdbcTemplate().queryForObject(query, new SalaryMapper());
		} catch (EmptyResultDataAccessException e) {
			//e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Query : " + query);
		System.out.println(
				"SalaryService.getPreviousCalculatedSalary took time in millisec:" + (System.currentTimeMillis() - start));
		return salary;
	}

	public boolean insertSalary(Salary salary) {
		boolean status = false;
		int insStatus = -1;
		long start = System.currentTimeMillis();
		String query = null;
		try {

			// String selQuery = attendance.selectQuery();
			// int count = jdbcService.geJdbcTemplate().queryForInt(selQuery);
			query = salary.insert();
			insStatus = jdbcService.geJdbcTemplate().update(query);
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("SalaryService.paySalary Query [" + query + "] insStatus[" + insStatus + "] Status[" + status
				+ "] took time in millisec:" + (System.currentTimeMillis() - start));

		return status;
	}
	
	public boolean updateSalary(Salary salary) {
		boolean status = false;
		int insStatus = -1;
		long start = System.currentTimeMillis();
		String query = null;
		try {

			// String selQuery = attendance.selectQuery();
			// int count = jdbcService.geJdbcTemplate().queryForInt(selQuery);
			query = salary.update();
			insStatus = jdbcService.geJdbcTemplate().update(query);

			// Creating Entries in Payment CDR
			if (salary.getInclude_advance() < 0) {
				Payment payment = new Payment();
				payment.setAmount(salary.getInclude_advance());
				payment.setLabour_id(salary.getLabour_id());
				payment.setIndustry_id(salary.getIndustry_id());
				payment.setRemarks("Deduction from Salary");
				paymentService.giveAdvance(payment);
			}

			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("SalaryService.paySalary Query [" + query + "] insStatus[" + insStatus + "] Status[" + status
				+ "] took time in millisec:" + (System.currentTimeMillis() - start));

		return status;
	}

	public int getHolidaysCount(String industry_id,String fromDate, String toDate){
		long start = System.currentTimeMillis();
		int count = 0;
		String selQuery=null;
		try {
			selQuery = "select count(*) from calender_days_holidays where industry_id='"+industry_id+"' and Holiday_DATE between '"+fromDate+"' and '"+toDate+"'";
			count = jdbcService.geJdbcTemplate().queryForObject(selQuery, Integer.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("SalaryService.getHolidaysCount Query [" + selQuery + "] Count[" + count
				+ "] took time in millisec:" + (System.currentTimeMillis() - start));
		return count;
	}
	public Salary getAttendanceSummary(Labour labour, String fromDate, String toDate) {
		Salary salary = new Salary();
		long start = System.currentTimeMillis();
		String query = null;
		try {
			query = "select * from attendance_book where industry_id='" + labour.getIndustry_id() + "' and labour_id='"
					+ labour.getLabour_id() + "' and attendance_date between '" + fromDate + "'  and '" + toDate + "'";
			List<Attendance> rows = jdbcService.geJdbcTemplate().query(query, new AttendanceMapper());

			String weekOffs[] = labour.getAdminDetail().getWeekoff_days().split("~");

			for (Attendance attendance : rows) { 
				if (attendance.getStatus() == 0) {
					salary.setAbsent(salary.getAbsent() + 1);
				}
				// Present
				else if (attendance.getStatus() == 1) {

					int weekday = getDayOfWeek(attendance.getDatetime());
					// if(weekday == Integer.parseInt(weekOffs[0]) || weekday ==
					// Integer.parseInt(weekOffs[1]))
					if (weekday == Integer.parseInt(weekOffs[0])) {
						salary.setOvertime_hours(salary.getOvertime_hours() + (attendance.getWorking_minutes() / 60));
					} else {
						salary.setPresent(salary.getPresent() + 1);
						int workingMinutes  =labour.getAdminDetail().getWorking_hours_in_day() * 60;
						int workedMinutes = attendance.getWorking_minutes();
						if(workedMinutes > workingMinutes){
							salary.setOvertime_hours(salary.getOvertime_hours() + ((workedMinutes - workingMinutes) / 60));
							salary.setTotal_hours_worked(salary.getTotal_hours_worked() + workingMinutes / 60);
						}else{
							salary.setTotal_hours_worked(salary.getTotal_hours_worked() + attendance.getWorking_minutes() / 60);
						}
					}
				}else if(attendance.getStatus() == 10 || attendance.getStatus() == 11){
					salary.setTotal_hours_worked(salary.getTotal_hours_worked() + labour.getAdminDetail().getWorking_hours_in_day());
				}
				// HalfDay
				else if (attendance.getStatus() == 2) {
				}
				// Leave
				else if (attendance.getStatus() == 3) {
					salary.setLeaves(salary.getLeaves() + 1);
				}
				// WeelyOff
				else if (attendance.getStatus() == 4) {
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		salary.setLabour_id(labour.getLabour_id());
		salary.setIndustry_id(labour.getIndustry_id());
		System.out.println("SalaryService.getAttendanceSummary  Query[" + query + "] TotalPresents["
				+ salary.getPresent() + "] TotalHoursWorked[" + salary.getTotal_hours_worked() + "] Total");
		System.out.println(
				"SalaryService.getAttendanceSummary took time in millisec:" + (System.currentTimeMillis() - start));

		return salary;
	}

	public boolean isIndustryHasHoliday(String industry_id,String date) {
		boolean status = false;
		long start = System.currentTimeMillis();
		String query = "select count(*) from calender_days_holidays where industry_id='"+industry_id+"' and DATE(Holiday_DATE) = '"+date+"'";
		
		try {
			int count = jdbcService.geJdbcTemplate().queryForObject(query, Integer.class);
			if(count > 0 )
				status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("SalaryService.isIndustryHasHoliday Query["+query+"] status ["+status+"] took time in millisec:" + (System.currentTimeMillis() - start));

		return status;
	}
	
	public static int getDayOfWeek(String dateTime) {
		int day = 0;
		try {
			Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(dateTime.split(" ")[0]);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date1);
			day = cal.get(Calendar.DAY_OF_WEEK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return day;
	}
	
}

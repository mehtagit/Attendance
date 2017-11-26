package com.ecom.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.atttendance.pojos.Salary;


public class SalaryMapper implements RowMapper<Salary> {

	private static SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Override
	public Salary mapRow(ResultSet rs, int rownum) throws SQLException {
		Date date = new Date();
		
		Salary salary = new Salary();
		salary.setId(rs.getString("id"));
		salary.setLabour_id(rs.getString("labour_id"));
		salary.setTotal_days(rs.getInt("total_days"));
		salary.setPresent(rs.getInt("present"));
		salary.setSalary_in_day(rs.getFloat("salary_in_day"));
		salary.setTotal_salary(rs.getFloat("total_salary"));
		salary.setLeaves(rs.getInt("Leaves"));
		salary.setLeaves_amount(rs.getFloat("Leaves_amount"));
		salary.setAbsent(rs.getInt("Absent"));
		salary.setAbsent_amount(rs.getFloat("Absent_amount"));
		salary.setTotal_hours(rs.getInt("total_hours"));
		salary.setTotal_hours_worked(rs.getInt("total_hours_worked"));
		salary.setInclude_overtime_amount(rs.getFloat("include_overtime_amount"));
		salary.setInclude_absent_amount(rs.getFloat("include_absent_amount"));
		salary.setOvertime_hours(rs.getInt("overtime_hours"));
		salary.setOvertime_amount(rs.getFloat("overtime_amount"));
		salary.setAdvance_amount(rs.getFloat("advance_amount"));
		salary.setInclude_advance(rs.getFloat("include_advance"));
		salary.setNet_salary(rs.getFloat("net_salary"));
		salary.setPaid_net_salary(rs.getFloat("paid_net_salary"));
		
		salary.setNet_salary(rs.getFloat("Net_salary"));
		salary.setOvertime_amount(rs.getFloat("Overtime_amount"));
		salary.setOvertime_hours(rs.getInt("Overtime_hours"));
		
		if(rs.getTimestamp("To_date")!=null)
		{	
			date.setTime(rs.getTimestamp("To_date").getTime());
			salary.setTo_date(dateformat.format(date));
		}
		if(rs.getTimestamp("From_date")!=null)
		{	
			date.setTime(rs.getTimestamp("From_date").getTime());
			salary.setFrom_date(dateformat.format(date));
		}
		salary.setTotal_days(rs.getInt("Total_days"));
		salary.setTotal_salary(rs.getFloat("Total_salary"));
		salary.setStatus(rs.getString("status") == null || rs.getInt("status")==0 ? false : true );
		
		if(rs.getTimestamp("created_on")!=null)
		{	
			date.setTime(rs.getTimestamp("created_on").getTime());
			salary.setCreated_on(dateformat.format(date));
		}
		
		if(rs.getTimestamp("modified_on")!=null)
		{	
			date.setTime(rs.getTimestamp("modified_on").getTime());
			salary.setModified_on(dateformat.format(date));
		}
		salary.setIndustry_id(rs.getString("industry_id"));
		return salary;
	}

}

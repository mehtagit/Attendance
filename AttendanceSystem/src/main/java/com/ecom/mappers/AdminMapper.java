package com.ecom.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.atttendance.pojos.AdminDetail;

public class AdminMapper implements RowMapper<AdminDetail>{

	private static SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Override
	public AdminDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
		Date date = new Date();
		AdminDetail adminDetail = new AdminDetail();
		if(rs.getTimestamp("created_on")!=null)
		{	
			date.setTime(rs.getTimestamp("created_on").getTime());
			adminDetail.setCreated_on(dateformat.format(date));
		}
		if(rs.getTimestamp("from_date")!=null)
		{
			date.setTime(rs.getTimestamp("from_date").getTime());
			adminDetail.setFrom_date(dateformat.format(date));
		}
		adminDetail.setId(rs.getString("id"));
		adminDetail.setIndustry_id(rs.getString("industry_id"));
		adminDetail.setLabour_id(rs.getString("labour_id"));
		adminDetail.setLeave_balance(rs.getInt("leave_balance"));
		if(rs.getTimestamp("modified_on")!=null)
		{
			date.setTime(rs.getTimestamp("modified_on").getTime());
			adminDetail.setModified_on(dateformat.format(date));
		}
		adminDetail.setMonthly_limit(rs.getInt("monthly_limit"));
		adminDetail.setOvertime_hour_amount(rs.getInt("overtime_hour_amount"));
		if(rs.getTimestamp("to_date")!=null)
		{
			date.setTime(rs.getTimestamp("to_date").getTime());
			adminDetail.setTo_date(dateformat.format(date));
		}
		adminDetail.setTotal_leaves(rs.getInt("total_leaves"));
		adminDetail.setTotal_salary(rs.getFloat("total_salary"));
		adminDetail.setUsed_leaves(rs.getInt("used_leaves"));
		adminDetail.setWeekoff_days(rs.getString("weekoff_days"));
		adminDetail.setWorking_hours_in_day(rs.getInt("working_hours_in_day"));		
		return adminDetail;
	}

}

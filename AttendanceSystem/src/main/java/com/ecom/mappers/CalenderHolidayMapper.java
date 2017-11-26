package com.ecom.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.atttendance.pojos.CalenderHoliday;

public class CalenderHolidayMapper implements RowMapper<CalenderHoliday>{

	private static SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	@Override
	public CalenderHoliday mapRow(ResultSet rs, int rowNum) throws SQLException {
		Date date = new Date();
		CalenderHoliday calenderHoliday = new CalenderHoliday();
		if(rs.getTimestamp("Holiday_DATE")!=null)
		{	
			date.setTime(rs.getTimestamp("Holiday_DATE").getTime());
			calenderHoliday.setDate(dateformat.format(date));
		}
		calenderHoliday.setID(rs.getString("id"));
		calenderHoliday.setRemarks(rs.getString("remarks"));
		calenderHoliday.setIndustry_id(rs.getString("industry_id"));
		return calenderHoliday;
	}

}

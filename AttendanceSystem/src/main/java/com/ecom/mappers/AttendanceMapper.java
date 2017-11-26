package com.ecom.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.atttendance.pojos.Attendance;

public class AttendanceMapper implements RowMapper<Attendance>{

	private static SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Override
	public Attendance mapRow(ResultSet rs, int rownum) throws SQLException {
		Date date = new Date();
		Attendance attendance = new Attendance(rs.getString("id"));
		attendance.setLabour_id(rs.getString("labour_id"));
		if(rs.getTimestamp("in_time")!=null)
		{	
			date.setTime(rs.getTimestamp("in_time").getTime());
			attendance.setIn_time(dateformat.format(date));
			attendance.setInTimeDisplayed(true);
		}
		if(rs.getTimestamp("attendance_date")!=null){
			date.setTime(rs.getTimestamp("attendance_date").getTime());
			attendance.setDatetime(dateformat.format(date));
		}
		if(rs.getTimestamp("out_time")!=null){
			date.setTime(rs.getTimestamp("out_time").getTime());
			attendance.setOut_time(dateformat.format(date));
			attendance.setOutTimeDisplayed(true);
		}
		attendance.setWorking_minutes(rs.getInt("working_minutes"));
		attendance.setStatus(rs.getString("status") == null || rs.getInt("status") == 0 ? 0 : rs.getInt("status"));
		attendance.setCreatedOn(rs.getString("created_on"));
		//attendance.setModifiedOn(rs.getString("modified_on"));
		attendance.setIndustry_id(rs.getString("industry_id"));
		return attendance;
	}

}

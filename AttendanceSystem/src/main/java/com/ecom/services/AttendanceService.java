package com.ecom.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.atttendance.pojos.Attendance;
import com.atttendance.pojos.AttendanceHistory;
import com.atttendance.pojos.Labour;
import com.ecom.mappers.AttendanceMapper;

@Service
public class AttendanceService {

	@Autowired
	private JDBCTemplateService jdbcService;

	final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public boolean markAttendance(Attendance attendance) {
		boolean status = false;
		int insStatus = -1;
		long start = System.currentTimeMillis();
		try {

			String selQuery = attendance.selectQuery();
			//int count = jdbcService.geJdbcTemplate().queryForInt(selQuery);
			int count = jdbcService.geJdbcTemplate().queryForObject(selQuery,Integer.class);
			
			String query = null;
			if (count > 0)
				query = attendance.updateQuery();
			else
				query = attendance.insertQuery();

			insStatus = jdbcService.geJdbcTemplate().update(query);
			status = true;
			System.out.println("AttendanceService.markAttendance Query[" + query + "]");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("AttendanceService.markAttendance insStatus[" + insStatus + "] Status[" + status
				+ "] took time in millisec:" + (System.currentTimeMillis() - start));

		return status;
	}

	public Attendance getCurrentAttendance(Labour labour) {
		boolean status = false;
		int insStatus = -1;
		long start = System.currentTimeMillis();
		String selQuery = null;
		Attendance attendance = null;
		try {

			selQuery = "select * from attendance_book where industry_id=? and labour_id=? and  attendance_date >= CURDATE()";
			attendance = jdbcService.geJdbcTemplate().queryForObject(selQuery, new String[] { labour.getIndustry_id(), labour.getLabour_id() },
					new AttendanceMapper());
			System.out.println("AttendanceService.getCurrentAttendance Query[" + selQuery + "]");
		}catch (EmptyResultDataAccessException e) {
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("selQuery ["+selQuery+"]");
		System.out.println("AttendanceService.getCurrentAttendance insStatus[" + insStatus + "] Status[" + status
				+ "] took time in millisec:" + (System.currentTimeMillis() - start));

		return attendance;
	}
	
	public AttendanceHistory makeSummary(Labour labour) {
		AttendanceHistory attendanceHistory = new AttendanceHistory();
		long start = System.currentTimeMillis();
		try {

			String query = attendanceHistory.queryLatestMonths();
			List<Attendance> attendanceList = jdbcService.geJdbcTemplate().query(query,
					new String[] { labour.getIndustry_id(), labour.getLabour_id() }, new AttendanceMapper());
			attendanceHistory.setAttendanceList(attendanceList);
			/*
			 * for(Attendance obj : attendanceList){
			 * attendanceHistory.getAttendanceDetailMap().get(); }
			 */
		} catch (EmptyResultDataAccessException e) {
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("AttendanceService.makeSummary took time in millisec:" + (System.currentTimeMillis() - start));

		return attendanceHistory;
	}

	public List<Attendance> refresh(String industry_id) {
		List<Attendance> attendanceList = null;
		long start = System.currentTimeMillis();
		String query = "select * from attendance_book where industry_id=? and attendance_date between '" + getTommorowDate(0) + "'  and '" + getTommorowDate(1) + "'";
		try {

			attendanceList = jdbcService.geJdbcTemplate().query(query,
					new String[] { industry_id }, new AttendanceMapper());
			
		} catch (EmptyResultDataAccessException e) {
			attendanceList = new ArrayList<Attendance>();
		} catch (Exception e) {
			attendanceList = new ArrayList<Attendance>();
			e.printStackTrace();
		}
		System.out.println("AttendanceService.refresh query["+query+"] took time in millisec:" + (System.currentTimeMillis() - start));

		return attendanceList;
	}
	
	
	public List<Attendance> getYesterdayAttendanceList(String industry_id) {
		List<Attendance> attendanceList = null;
		long start = System.currentTimeMillis();
		String query = "select * from attendance_book where industry_id=? and attendance_date between '" + getTommorowDate(-1) + "'  and '" + getTommorowDate(0) + "'";
		try {

			attendanceList = jdbcService.geJdbcTemplate().query(query,
					new String[] { industry_id }, new AttendanceMapper());
			
		} catch (EmptyResultDataAccessException e) {
			attendanceList = new ArrayList<Attendance>();
		} catch (Exception e) {
			attendanceList = new ArrayList<Attendance>();
			e.printStackTrace();
		}
		System.out.println("AttendanceService.getYesterdayAttendanceList query["+query+"] took time in millisec:" + (System.currentTimeMillis() - start));

		return attendanceList;
	}
	
	public String getTommorowDate(int days) {

		final Date date = new Date();
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, days);
		return dateFormat.format(calendar.getTime()) + " 00:00:00";
	}
}

package com.atttendance.pojos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AttendanceHistory {
	
	private int noOfMonths=3;
	private List<Attendance> attendanceList;
	private HashMap<Integer, List<AttendanceSummary>> attendanceSummaryMap;
	private ArrayList<Integer> sortedMonthList;
	
	public AttendanceHistory(){
	}

	public void setNoOfMonths(int noOfMonths) {
		this.noOfMonths = noOfMonths;
	}

	public void setAttendanceSummaryMap(HashMap<Integer, List<AttendanceSummary>> attendanceSummaryMap) {
		this.attendanceSummaryMap = attendanceSummaryMap;
	}

	public void setSortedMonthList(ArrayList<Integer> sortedMonthList) {
		this.sortedMonthList = sortedMonthList;
	}

	public int getNoOfMonths() {
		return noOfMonths;
	}
	
	public List<Attendance> getAttendanceList() {
		return attendanceList;
	}
	public void setAttendanceList(List<Attendance> attendanceList) {
		this.attendanceList = attendanceList;
	}
	public HashMap<Integer, List<AttendanceSummary>> getAttendanceSummaryMap() {
		return attendanceSummaryMap;
	}
	
	public String queryLatestMonths(){
		return "select * from attendance_book where industry_id=? and labour_id=? and attendance_date BETWEEN NOW() - INTERVAL 90 DAY AND NOW() order by created_on asc";
	}
	public ArrayList<Integer> getSortedMonthList() {
		return sortedMonthList;
	}
	
	
	
}

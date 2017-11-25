package com.atttendance.pojos;

public class AttendanceSummary {
	private String monthName;
	private int noOfPresent;
	private int noOfAbsent;
	private int noOfLeaves;
	
	public String getMonthName() {
		return monthName;
	}
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	public int getNoOfPresent() {
		return noOfPresent;
	}
	public void setNoOfPresent(int noOfPresent) {
		this.noOfPresent = noOfPresent;
	}
	public int getNoOfAbsent() {
		return noOfAbsent;
	}
	public void setNoOfAbsent(int noOfAbsent) {
		this.noOfAbsent = noOfAbsent;
	}
	public int getNoOfLeaves() {
		return noOfLeaves;
	}
	public void setNoOfLeaves(int noOfLeaves) {
		this.noOfLeaves = noOfLeaves;
	}
}

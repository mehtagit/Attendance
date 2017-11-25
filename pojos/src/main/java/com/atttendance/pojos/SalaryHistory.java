package com.atttendance.pojos;

import java.util.HashMap;
import java.util.List;
public class SalaryHistory {
	private int noOfMonths=3;
	private HashMap<Integer, List<Salary>> salaryDetailMap;
	private List<Salary> salaryList;
	public int getNoOfMonths() {
		return noOfMonths;
	}
	public void setNoOfMonths(int noOfMonths) {
		this.noOfMonths = noOfMonths;
	}
	public HashMap<Integer, List<Salary>> getSalaryDetailMap() {
		return salaryDetailMap;
	}
	public void setSalaryDetailMap(HashMap<Integer, List<Salary>> salaryDetailMap) {
		this.salaryDetailMap = salaryDetailMap;
	}

	public List<Salary> getSalaryList() {
		return salaryList;
	}

	public void setSalaryList(List<Salary> salaryList) {
		this.salaryList = salaryList;
	}
}

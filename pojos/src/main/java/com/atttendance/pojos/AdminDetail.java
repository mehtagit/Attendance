package com.atttendance.pojos;

public class AdminDetail {

	private String id;
	private String industry_id;
	private String labour_id;
	private float total_salary;
	private int total_leaves;
	private int leave_balance;
	private int used_leaves;
	private int monthly_limit;
	private int working_hours_in_day;
	private String weekoff_days;
	private int overtime_hour_amount;
	private String from_date;
	private String to_date;
	private String created_on;
	private String modified_on;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIndustry_id() {
		return industry_id;
	}

	public void setIndustry_id(String industry_id) {
		this.industry_id = industry_id;
	}

	public String getLabour_id() {
		return labour_id;
	}

	public void setLabour_id(String labour_id) {
		this.labour_id = labour_id;
	}

	public float getTotal_salary() {
		return total_salary;
	}

	public void setTotal_salary(float total_salary) {
		this.total_salary = total_salary;
	}

	public int getTotal_leaves() {
		return total_leaves;
	}

	public void setTotal_leaves(int total_leaves) {
		this.total_leaves = total_leaves;
	}

	public int getLeave_balance() {
		return leave_balance;
	}

	public void setLeave_balance(int leave_balance) {
		this.leave_balance = leave_balance;
	}

	public int getUsed_leaves() {
		return used_leaves;
	}

	public void setUsed_leaves(int used_leaves) {
		this.used_leaves = used_leaves;
	}

	public int getMonthly_limit() {
		return monthly_limit;
	}

	public void setMonthly_limit(int monthly_limit) {
		this.monthly_limit = monthly_limit;
	}

	public int getWorking_hours_in_day() {
		return working_hours_in_day;
	}

	public void setWorking_hours_in_day(int working_hours_in_day) {
		this.working_hours_in_day = working_hours_in_day;
	}

	

	public String getWeekoff_days() {
		return weekoff_days;
	}

	public void setWeekoff_days(String weekoff_days) {
		this.weekoff_days = weekoff_days;
	}

	public int getOvertime_hour_amount() {
		return overtime_hour_amount;
	}

	public void setOvertime_hour_amount(int overtime_hour_amount) {
		this.overtime_hour_amount = overtime_hour_amount;
	}

	public String getFrom_date() {
		return from_date;
	}

	public void setFrom_date(String from_date) {
		this.from_date = from_date;
	}

	public String getTo_date() {
		return to_date;
	}

	public void setTo_date(String to_date) {
		this.to_date = to_date;
	}

	public String getCreated_on() {
		return created_on;
	}

	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}

	public String getModified_on() {
		return modified_on;
	}

	public void setModified_on(String modified_on) {
		this.modified_on = modified_on;
	}

	public String insert(String id, String industry_id) {
		String from = null, to = null;
		labour_id = id;
		if (from_date != null)
			from = "STR_TO_DATE('" + from_date + "','%Y-%m-%d %H:%i:%s')";
		if (to_date != null)
			to = "STR_TO_DATE('" + to_date + "','%Y-%m-%d %H:%i:%s')";
		return "insert into admin_detail (id,industry_id,labour_id,total_salary,total_leaves,leave_balance,used_leaves,monthly_limit,working_hours_in_day,weekoff_days,overtime_hour_amount,from_date,to_date,status) values ('"
				+ id + "','" + industry_id + "','" + labour_id + "'," + total_salary + "," + total_leaves + ","
				+ leave_balance + "," + used_leaves + "," + monthly_limit + "," + working_hours_in_day + ",'"
				+ weekoff_days + "'," + overtime_hour_amount + "," + from + "," + to + ",1)";
	}

	public String update() {
		return "update admin_detail total_salary=" + total_salary + ",total_leaves=" + total_leaves
				+ " where industry_id='"+industry_id+"' and labour_id='"+labour_id+"' and id='"+id+"' ";
	}
}

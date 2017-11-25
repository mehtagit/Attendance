package com.atttendance.pojos;

public class Salary {
	private String id;
	private String labour_id;
	private int total_days;
	private float salary_in_day;
	private float total_salary;
	private int present;
	private int leaves;
	private float leaves_amount;
	private float include_leaves_amount;
	private int absent;
	private float absent_amount;
	private float include_absent_amount;
	private int total_hours;
	private float total_hours_worked;
	private int overtime_hours;
	private float overtime_amount;
	private float include_overtime_amount;
	private float advance_amount;
	private float include_advance;
	private float net_salary;
	private float paid_net_salary;
	private String to_date;
	private String from_date;
	private boolean status;
	private String created_on;
	private String modified_on;
	private String industry_id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabour_id() {
		return labour_id;
	}

	public void setLabour_id(String labour_id) {
		this.labour_id = labour_id;
	}

	public int getTotal_days() {
		return total_days;
	}

	public void setTotal_days(int total_days) {
		this.total_days = total_days;
	}

	public float getSalary_in_day() {
		return salary_in_day;
	}

	public void setSalary_in_day(float salary_in_day) {
		this.salary_in_day = salary_in_day;
	}

	public float getTotal_salary() {
		return total_salary;
	}

	public void setTotal_salary(float total_salary) {
		this.total_salary = total_salary;
	}

	public int getLeaves() {
		return leaves;
	}

	public float getInclude_absent_amount() {
		return include_absent_amount;
	}

	public void setInclude_absent_amount(float include_absent_amount) {
		this.include_absent_amount = include_absent_amount;
	}

	public float getInclude_overtime_amount() {
		return include_overtime_amount;
	}

	public void setInclude_overtime_amount(float include_overtime_amount) {
		this.include_overtime_amount = include_overtime_amount;
	}

	public void setLeaves(int leaves) {
		this.leaves = leaves;
	}

	public float getLeaves_amount() {
		return leaves_amount;
	}

	public void setLeaves_amount(float leaves_amount) {
		this.leaves_amount = leaves_amount;
	}

	public int getAbsent() {
		return absent;
	}

	public void setAbsent(int absent) {
		this.absent = absent;
	}

	public float getAbsent_amount() {
		return absent_amount;
	}

	public void setAbsent_amount(float absent_amount) {
		this.absent_amount = absent_amount;
	}

	public int getOvertime_hours() {
		return overtime_hours;
	}

	public void setOvertime_hours(int overtime_hours) {
		this.overtime_hours = overtime_hours;
	}

	public float getOvertime_amount() {
		return overtime_amount;
	}

	public void setOvertime_amount(float overtime_amount) {
		this.overtime_amount = overtime_amount;
	}

	public float getNet_salary() {
		return net_salary;
	}

	public void setNet_salary(float net_salary) {
		this.net_salary = net_salary;
	}

	public String getTo_date() {
		return to_date;
	}

	public void setTo_date(String to_date) {
		this.to_date = to_date;
	}

	public String getFrom_date() {
		return from_date;
	}

	public void setFrom_date(String from_date) {
		this.from_date = from_date;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
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

	public String getIndustry_id() {
		return industry_id;
	}

	public void setIndustry_id(String industry_id) {
		this.industry_id = industry_id;
	}

	public int getTotal_hours() {
		return total_hours;
	}

	public void setTotal_hours(int total_hours) {
		this.total_hours = total_hours;
	}

	public float getTotal_hours_worked() {
		return total_hours_worked;
	}

	public void setTotal_hours_worked(float total_hours_worked) {
		this.total_hours_worked = total_hours_worked;
	}

	public float getAdvance_amount() {
		return advance_amount;
	}

	public void setAdvance_amount(float advance_amount) {
		this.advance_amount = advance_amount;
	}

	public float getInclude_advance() {
		return include_advance;
	}

	public void setInclude_advance(float include_advance) {
		this.include_advance = include_advance;
	}

	public float getPaid_net_salary() {
		return paid_net_salary;
	}

	public void setPaid_net_salary(float paid_net_salary) {
		this.paid_net_salary = paid_net_salary;
	}

	public int getPresent() {
		return present;
	}

	public void setPresent(int present) {
		this.present = present;
	}

	public String insert() {
		String FD = null, TD = null;
		if (from_date != null)
			FD = "STR_TO_DATE('" + from_date + "','%Y-%m-%d %H:%i:%s')";
		if (to_date != null)
			TD = "STR_TO_DATE('" + to_date + "','%Y-%m-%d %H:%i:%s')";
		String query = "insert into salary_book (id,labour_id,total_days,salary_in_day,total_salary,present,leaves,leaves_amount,include_leaves_amount,absent,absent_amount,total_hours,total_hours_worked,overtime_hours,overtime_amount,advance_amount,net_salary,status,from_date,to_Date,industry_id) values ('"
				+ id + "','" + labour_id + "'," + total_days + "," + salary_in_day + "," + total_salary + "," + present
				+ "," + leaves + "," + leaves_amount + "," + include_leaves_amount + "," + absent + "," + absent_amount
				+ "," + total_hours + "," + total_hours_worked + "," + overtime_hours
				+ "," + overtime_amount + "," + advance_amount + "," + net_salary + "," + status + "," + FD + "," + TD
				+ ",'" + industry_id + "') ";
		return query;
	}

	public String update() {

		String query = "update salary_book set include_overtime_amount=" + include_overtime_amount
				+ " , include_absent_amount=" + include_absent_amount + ", include_advance=" + include_advance + " , paid_net_salary=" + paid_net_salary
				+ ", status=1 where industry_id='" + industry_id + "' and labour_id='" + labour_id + "' and id='" + id + "'";
		return query;
	}

	@Override
	public String toString() {
		return "Salary [id=" + id + ", labour_id=" + labour_id + ", total_days=" + total_days + ", salary_in_day="
				+ salary_in_day + ", total_salary=" + total_salary + ", present=" + present + ", leaves=" + leaves
				+ ", leaves_amount=" + leaves_amount + ", absent=" + absent + ", absent_amount=" + absent_amount
				+ ", total_hours=" + total_hours + ", total_hours_worked=" + total_hours_worked + ", overtime_hours="
				+ overtime_hours + ", overtime_amount=" + overtime_amount + ", advance_amount=" + advance_amount
				+ ", include_advance=" + include_advance + ", net_salary=" + net_salary + ", paid_net_salary="
				+ paid_net_salary + ", to_date=" + to_date + ", from_date=" + from_date + ", status=" + status
				+ ", created_on=" + created_on + ", modified_on=" + modified_on + ", industry_id=" + industry_id + "]";
	}

}

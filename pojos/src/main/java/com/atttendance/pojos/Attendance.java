package com.atttendance.pojos;

public class Attendance {
	
	private String id;
	private String labour_id;
	private String in_time;
	private String out_time;
	private String datetime;
	private int working_minutes;
	private String createdOn;
	private String modifiedOn;
	private int status;
	private String industry_id;
	boolean isInTimeDisplayed = false;
	boolean isOutTimeDisplayed = false;

	public Attendance(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getLabour_id() {
		return labour_id;
	}

	public void setLabour_id(String labour_id) {
		this.labour_id = labour_id;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getIn_time() {
		return in_time;
	}

	public void setIn_time(String in_time) {
		this.in_time = in_time;
	}

	public String getOut_time() {
		return out_time;
	}

	public void setOut_time(String out_time) {
		this.out_time = out_time;
	}

	public int getStatus() {
		return status;
	}

	public boolean isInTimeDisplayed() {
		return isInTimeDisplayed;
	}

	public void setInTimeDisplayed(boolean inTimeDisplayed) {
		isInTimeDisplayed = inTimeDisplayed;
	}

	public boolean isOutTimeDisplayed() {
		return isOutTimeDisplayed;
	}

	public void setOutTimeDisplayed(boolean outTimeDisplayed) {
		isOutTimeDisplayed = outTimeDisplayed;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getIndustry_id() {
		return industry_id;
	}

	public void setIndustry_id(String industry_id) {
		this.industry_id = industry_id;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(String modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public int getWorking_minutes() {
		return working_minutes;
	}

	public void setWorking_minutes(int working_minutes) {
		this.working_minutes = working_minutes;
	}

	public String insertQuery() {
		String in = null, out = null, attendaceDate = "NOW()";
		if (in_time != null)
			in = "STR_TO_DATE('" + in_time + "','%Y-%m-%d %H:%i:%s')";
		if (out_time != null)
			out = "STR_TO_DATE('" + out_time + "','%Y-%m-%d %H:%i:%s')";
		if (datetime != null)
			attendaceDate = "STR_TO_DATE('" + datetime + "','%Y-%m-%d %H:%i:%s')";

		return "insert into attendance_book (id,labour_id,in_time,out_time,attendance_date,status,industry_id) values ('"
				+ id + "','" + labour_id + "'," + in + "," + out + "," + attendaceDate + "," + status + ",'"
				+ industry_id + "')";
	}

	public String updateQuery() {
		String in = null, out = null;
		if (in_time != null)
			in = "STR_TO_DATE('" + in_time + "','%Y-%m-%d %H:%i:%s')";
		if (out_time != null)
			out = "STR_TO_DATE('" + out_time + "','%Y-%m-%d %H:%i:%s')";
		return "update attendance_book set in_time=" + in + ", out_time=" + out
				+ ", working_minutes=TIMESTAMPDIFF(MINUTE,in_time,out_time), status=" + status + " where industry_id='"
				+ industry_id + "' and  id='" + id + "' and labour_id='" + labour_id + "'";
	}

	public String selectQuery() {
		return "select count(*) from attendance_book where industry_id='" + industry_id + "' and id='" + id + "'";
	}

}

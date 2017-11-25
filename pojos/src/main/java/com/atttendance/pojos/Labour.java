package com.atttendance.pojos;

import java.util.ArrayList;
import java.util.List;

public class Labour {

	private String labour_id;
	private String mobile_no;
	private String firstname;
	private String lastname;
	private String sex;
	private int level;
	private String pin_identity;
	private String address;
	private String city;
	private String State;
	private String country;
	private String releaving_date;
	private String photo;
	private String Industry_id;
	private boolean status;
	private String createdOn;
	private String modifiedOn;
	private Attendance attendance;
	private Salary salary;
	private AdminDetail adminDetail;
	private AttendanceHistory attendanceHistory;
	private SalaryHistory salaryHistory;

	public String getLabour_id() {
		return labour_id;
	}

	public void setLabour_id(String labour_id) {
		this.labour_id = labour_id;
	}

	public String getFirstname() {
		return firstname;
	}

	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPin_identity() {
		return pin_identity;
	}

	public void setPin_identity(String pin_identity) {
		this.pin_identity = pin_identity;
	}

	public String getReleaving_date() {
		return releaving_date;
	}

	public void setReleaving_date(String releaving_date) {
		this.releaving_date = releaving_date;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
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

	public String getIndustry_id() {
		return Industry_id;
	}

	public void setIndustry_id(String industry_id) {
		Industry_id = industry_id;
	}

	public Attendance getAttendance() {
		return attendance;
	}

	public void setAttendance(Attendance attendance) {
		this.attendance = attendance;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	public AttendanceHistory getAttendanceHistory() {
		return attendanceHistory;
	}

	public void setAttendanceHistory(AttendanceHistory attendanceHistory) {
		this.attendanceHistory = attendanceHistory;
	}

	public SalaryHistory getSalaryHistory() {
		return salaryHistory;
	}

	public Salary getSalary() {
		return salary;
	}

	public void setSalary(Salary salary) {
		this.salary = salary;
	}

	public void setSalaryHistory(SalaryHistory salaryHistory) {
		this.salaryHistory = salaryHistory;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public AdminDetail getAdminDetail() {
		return adminDetail;
	}

	public void setAdminDetail(AdminDetail adminDetail) {
		this.adminDetail = adminDetail;
	}

	public List<String> insert(String id, String industry_id) {
		ArrayList<String> queriesList = new ArrayList<String>();
		this.labour_id = id;
		this.Industry_id = industry_id;
		
		String query =  "insert into labour_details (id,mobile_no,first_name,last_name,level_id,sex,pin_identification,Address,city,state,country,status,industry_id) values ('"
				+ id + "','" + mobile_no + "','" + firstname + "','" + lastname + "'," + level + ",'" + sex + "','"
				+ pin_identity + "','" + address + "','" + city + "','" + State + "','" + country + "',1,'"
				+ industry_id + "')";
		String queryPhoto = "insert into labour_photo (id,industry_id,photo) values ('"+id+"','"+industry_id+"','"+photo+"')";
		
		queriesList.add(query);
		queriesList.add(queryPhoto);
		
		return queriesList;
	}

	public List<String> update() {
		List<String> queryList = new ArrayList<String>();
		String query = "update labour_details set mobile_no='" + mobile_no + "',first_name='" + firstname + "',last_name='"
				+ lastname + "',level_id=" + level + ",pin_identification='" + pin_identity + "',Address='" + address
				+ "',city='" + city + "',state='" + State + "',country='" + country + "' where industry_id='"+Industry_id+"' and id='"+labour_id+"'";
		String query1 = "update labour_photo set photo='"+photo+"'  where industry_id='"+Industry_id+"' and id='"+labour_id+"'";
		
		queryList.add(query);
		queryList.add(query1);
		return queryList;
	}

	public String delete(){
		String query = "update labour_details set status=0  where industry_id='"+Industry_id+"' and id='"+labour_id+"'";
		return query;
	}
	@Override
	public String toString() {
		return "Labour [labour_id=" + labour_id + ", mobile_no=" + mobile_no + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", sex=" + sex + ", level=" + level + ", pin_identity=" + pin_identity
				+ ", address=" + address + ", city=" + city + ", State=" + State + ", country=" + country
				+ ", releaving_date=" + releaving_date + ", Industry_id=" + Industry_id + ", status=" + status
				+ ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn + ", attendance=" + attendance + ", salary="
				+ salary + ", adminDetail=" + adminDetail + ", attendanceHistory=" + attendanceHistory
				+ ", salaryHistory=" + salaryHistory + "]";
	}
	
	
	
}

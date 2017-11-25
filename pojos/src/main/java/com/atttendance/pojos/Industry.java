package com.atttendance.pojos;

public class Industry {
	private String industry_id;
	private String tin_no;
	private String name;
	private String owner_name;
	private String contact_no;
	private String registration_pin;
	private String address;
	private String created_on;
	private String modified_on;
	private String defaultOutTime;
	private int status;

	public String getIndustry_id() {
		return industry_id;
	}

	public void setIndustry_id(String industry_id) {
		this.industry_id = industry_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwner_name() {
		return owner_name;
	}

	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}

	public String getContact_no() {
		return contact_no;
	}

	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}

	public String getTin_no() {
		return tin_no;
	}

	public void setTin_no(String tin_no) {
		this.tin_no = tin_no;
	}

	public String getRegistration_pin() {
		return registration_pin;
	}

	public void setRegistration_pin(String registration_pin) {
		this.registration_pin = registration_pin;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getDefaultOutTime() {
		return defaultOutTime;
	}

	public void setDefaultOutTime(String defaultOutTime) {
		this.defaultOutTime = defaultOutTime;
	}

	public boolean isNew() {
		return (this.industry_id == null);
	}

	@Override
	public String toString() {
		return "Industry [industry_id=" + industry_id + ", tin_no=" + tin_no + ", name=" + name + ", owner_name="
				+ owner_name + ", contact_no=" + contact_no + ", registration_pin=" + registration_pin + ", address="
				+ address + ", created_on=" + created_on + ", modified_on=" + modified_on + ", status=" + status + "]";
	}

}

package com.atttendance.pojos;

/**
 * @author arun.mehta
 *
 */
public class Payment {

	private String payment_id;
	private String labour_id;
	private String payment_date;
	private float amount;
	private String remarks;
	private String createdOn;
	private String modifiedOn;
	private String industry_id;
	private boolean status;

	public String getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(String payment_id) {
		this.payment_id = payment_id;
	}

	public String getRemarks() {
		return remarks==null?remarks:remarks.trim().replace("\n", "");
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getIndustry_id() {
		return industry_id;
	}

	public void setIndustry_id(String industry_id) {
		this.industry_id = industry_id;
	}

	public String getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(String payment_date) {
		this.payment_date = payment_date;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getLabour_id() {
		return labour_id;
	}

	public void setLabour_id(String labour_id) {
		this.labour_id = labour_id;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String insert() {

		return "insert into payment_book (labour_id,amount,remarks,industry_id) values ('" + labour_id + "'," + amount
				+ ",'" + remarks + "','" + industry_id + "')";
	}

	public String update() {
		return "update payment_book set amount=amount+" + amount + " , remarks='" + getRemarks() + "'  where industry_id='"
				+ industry_id + "' and labour_id='" + labour_id + "'";
	}

	public String isExist() {
		return "select count(*) from payment_book  where industry_id='" + industry_id + "' and labour_id='" + labour_id
				+ "'";
	}

	public String insertCdr() {

		return "insert into payment_cdr_book (payment_id ,labour_id,amount,remarks,industry_id) values ('" + payment_id
				+ "','" + labour_id + "'," + amount + ",'" + getRemarks() + "','" + industry_id + "')";
	}

}

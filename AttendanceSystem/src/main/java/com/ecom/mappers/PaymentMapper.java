package com.ecom.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atttendance.pojos.Payment;

public class PaymentMapper  implements RowMapper<Payment> {

	@Override
	public Payment mapRow(ResultSet rs, int rownum) throws SQLException {
		Payment payment = new Payment();
		payment.setPayment_id(rs.getString("id"));
		payment.setLabour_id(rs.getString("labour_id"));
		payment.setPayment_date(rs.getString("payment_date"));
		payment.setAmount(rs.getFloat("amount"));
		payment.setRemarks(rs.getString("remarks"));
		payment.setPayment_date(rs.getString("created_on"));
		payment.setModifiedOn(rs.getString("modified_on"));
		payment.setIndustry_id(rs.getString("industry_id"));
		return payment;
	}

}

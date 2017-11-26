package com.ecom.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.atttendance.pojos.Labour;
import com.atttendance.pojos.Payment;
import com.atttendance.pojos.Salary;

@Service
public class PaymentService {

	@Autowired
	private JDBCTemplateService jdbcService;

	public Payment giveAdvance(Payment payment) {
		int insStatus = -1;
		int insStatusCdr = -1;
		String paymentQuery = null;
		String paymentCdrQuery = null;
		long start = System.currentTimeMillis();
		try {

			paymentCdrQuery = payment.insertCdr();
			int cnt = jdbcService.geJdbcTemplate().queryForObject(payment.isExist(), Integer.class);
			if (cnt > 0)
				paymentQuery = payment.update();
			else
				paymentQuery = payment.insert();
			
			insStatus = jdbcService.geJdbcTemplate().update(paymentQuery);
			insStatusCdr = jdbcService.geJdbcTemplate().update(paymentCdrQuery);
			payment.setStatus(true);
			System.out.println("PaymentService.giveAdvance paymentQuery["+paymentQuery+"] paymentCdrQuery[" + paymentCdrQuery + "]");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("PaymentService.giveAdvance insStatus[" + insStatus + "] insStatusCdr[" + insStatusCdr
				+ "] Status[" + payment.isStatus() + "] took time in millisec:" + (System.currentTimeMillis() - start));
		return payment;
	}

	public void fillMonthPaymentSummary(Labour labour, Salary salary, String fromDate, String toDate) {
		long start = System.currentTimeMillis();
		String query = null;
		try {
			query = "select amount from payment_book where industry_id='"
					+ labour.getIndustry_id() + "' and labour_id='" + labour.getLabour_id() + "'";
			int remaining_amount = jdbcService.geJdbcTemplate().queryForObject(query, Integer.class);

			salary.setAdvance_amount(remaining_amount);
		}catch (EmptyResultDataAccessException e) {
			salary.setAdvance_amount(0);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("PaymentService.getMonthSummary Query : " + query);
		System.out.println(
				"PaymentService.getMonthSummary took time in millisec:" + (System.currentTimeMillis() - start));
		return;
	}
	/*
	 * public LabourList getMonthlyPaymentList(String industry_id){ LabourList
	 * labourList = labourService.getLabourList(industry_id); for(Labour labour
	 * : labourList.getLabourList()){
	 * labour.setPaymentList(getPaymentList(industry_id,labour)); } return
	 * labourList; } public PaymentList getPaymentList(String industry_id ,
	 * Labour labour) { PaymentList paymentList = new PaymentList();
	 * List<Payment> list; long start = System.currentTimeMillis(); try { String
	 * query =
	 * "select * from advance_payment_book where industry_id=? and labour_id=?  and MONTH(created_on) = MONTH(CURRENT_DATE - INTERVAL 0 MONTH)"
	 * ; list = jdbcService.geJdbcTemplate().query(query, new String[] {
	 * industry_id,labour.getLabour_id() }, new PaymentMapper()); // For Gui
	 * purpose Payment addMore = new Payment(); addMore.setRemarks("Add More");
	 * list.add(addMore); paymentList.setList(list); } catch (Exception e) {
	 * e.printStackTrace(); }
	 * System.out.println("PaymentService.getPaymentList took time in millisec:"
	 * + (System.currentTimeMillis() - start));
	 * 
	 * return paymentList; }
	 */

}

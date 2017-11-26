package com.ecom.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.atttendance.pojos.Industry;
import com.ecom.mappers.IndustryMapper;

@Service
public class IndustryService {

	@Autowired
	private JDBCTemplateService jdbcService;

	public List<Industry> getListOfIndustries() {
		List<Industry> reqDataList = null;
		long start = System.currentTimeMillis();
		String query = "select * from industry_details";
		try {

			reqDataList = jdbcService.geJdbcTemplate().query(query, new IndustryMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("IndustryService.getListOfIndustries Query[" + query + "] took time in millisec:"
				+ (System.currentTimeMillis() - start));
		return reqDataList;
	}

	public Industry getIndustryDetail(Industry industry) {
		Industry hotelDetail = null;
		long start = System.currentTimeMillis();
		try {
			String query = "select * from industry_details where industry_id=? and registration_pin=?";
			hotelDetail = jdbcService.geJdbcTemplate().queryForObject(query,
					new String[] { industry.getIndustry_id(), industry.getRegistration_pin() }, new IndustryMapper());
		} catch (EmptyResultDataAccessException e) {
			hotelDetail = industry;
			industry.setStatus(-1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("DbService.getIndustryDetail took time in millisec:" + (System.currentTimeMillis() - start));
		return hotelDetail;
	}

	public void delete(String hotel_id, String tin_no) {
		// TODO Auto-generated method stub

	}

	public void update(Industry hotelBean, String hotel_id, String tin_no) {
		// TODO Auto-generated method stub

	}

}
